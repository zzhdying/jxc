package com.mglf.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.mglf.dao.datasource.DynamicDataSourceHolder;

/**
 * 动态数据源
 * 
 * @author zhongzhuohan
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}

}