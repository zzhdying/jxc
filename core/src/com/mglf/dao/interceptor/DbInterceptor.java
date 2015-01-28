package com.mglf.dao.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.mglf.base.BaseEntity;
import com.mglf.dao.datasource.PostgreTransactional;
import com.mglf.dao.dialect.Dialect;
import com.mglf.dao.dialect.MySql5Dialect;
import com.mglf.dao.dialect.OracleDialect;
import com.mglf.dao.dialect.PostgreDialect;
import com.mglf.dto.LoginUserDetails;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DbInterceptor implements Interceptor {

	private final static Log log = LogFactory.getLog(DbInterceptor.class);

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

	private static final ThreadLocal<Integer> resultCountTL = new ThreadLocal<Integer>();

	public static void setResultCount(int count) {
		resultCountTL.set(count);
	}

	public static int getResultCount() {
		return resultCountTL.get();
	}

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();

		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);

		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

		String clsName = mappedStatement.getId();
		clsName = clsName.substring(0, clsName.lastIndexOf('.'));
		Class cls = Class.forName(clsName);
		
		SqlCommandType type = mappedStatement.getSqlCommandType();
		if (!SqlCommandType.SELECT.equals(type)) {

			Object val = boundSql.getParameterObject();

			if (val instanceof BaseEntity) {

				LoginUserDetails principal = SpringSecurityUtils.getCurrentUser();
				if (!EmptyUtil.isEmpty(principal)) {
					BaseEntity be = (BaseEntity) val;
					
				}
			}

			return invocation.proceed();
		}

		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

		SearchPageableCondition searchPageableCondition = null;
		if (boundSql.getParameterObject() instanceof SearchPageableCondition){
			searchPageableCondition = (SearchPageableCondition)boundSql.getParameterObject();
		}
		
		if (!(boundSql.getParameterObject() instanceof Map)) {
			if(searchPageableCondition == null){
				return invocation.proceed();	
			}
		}
		
		Integer offset = null;
		Integer limit = null;
		

		if(searchPageableCondition == null){
			@SuppressWarnings("unchecked")
			Map<String, Object> parms = (Map<String, Object>) boundSql.getParameterObject();

			if(parms instanceof StrictMap){
				return invocation.proceed();
			}
			
			offset = (Integer) parms.get("offset");
			limit = (Integer) parms.get("limit");
			
		}else{
			offset = searchPageableCondition.getOffset();
			limit = searchPageableCondition.getLimit();
		}

		if (offset == null) {
			return invocation.proceed();
		}

		if ((rowBounds == null || rowBounds == RowBounds.DEFAULT) && (offset == null || limit == null)) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
		}
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
					+ configuration.getVariables().getProperty("dialect"));
		}

		Dialect dialect = null;
		if(cls.isAnnotationPresent(PostgreTransactional.class)){
			dialect = new PostgreDialect();
		}else{
			switch (databaseType) {
			case MYSQL:
				dialect = new MySql5Dialect();
				break;
			case ORACLE:
				dialect = new OracleDialect();
				break;
			}	
		}
		
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		String countSql = originalSql.toUpperCase();
		int orderIdx = countSql.lastIndexOf(" ORDER ");
		if (countSql.lastIndexOf(')') > orderIdx) {
			orderIdx = -1;
		}
		if (orderIdx > 0) {
			countSql = countSql.substring(0, orderIdx - 1);
		}
		countSql = "select count(*) from (" + countSql + ") t";

		BoundSql countBS = new BoundSql(configuration, countSql.toString(), boundSql.getParameterMappings(), boundSql.getParameterObject());
		Connection conn = configuration.getEnvironment().getDataSource().getConnection();
		PreparedStatement countStmt = conn.prepareStatement(countSql.toString());
		setParameters(countStmt, configuration, countBS, boundSql.getParameterObject());
		ResultSet rs = countStmt.executeQuery();
		int totpage = 0;
		if (rs.next()) {
			totpage = rs.getInt(1);
		}
		rs.close();
		countStmt.close();
		conn.close();

		setResultCount(totpage);

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, offset, limit));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (log.isDebugEnabled()) {
			log.debug("进入分页拦截器：生成的SQL为: " + boundSql.getSql());
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps, Configuration configuration, BoundSql boundSql, Object parameterObject)
			throws SQLException {
		// ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			// Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						// throw new
						// ExecutorException("There was no TypeHandler found for parameter "
						// + propertyName + " of statement " +
						// mappedStatement.getId());
//						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
//								+ " of statement ");
					}else{
						typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
					}
				}
			}
		}
	}
}
