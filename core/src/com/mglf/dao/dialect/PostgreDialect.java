package com.mglf.dao.dialect;

import com.mglf.dao.dialect.Dialect;
import com.mglf.dao.dialect.PostgrePageHepler;

import com.mglf.dao.dialect.Dialect;
import com.mglf.dao.dialect.PostgrePageHepler;


public class PostgreDialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	
	
	public String getLimitString(String sql, boolean hasOffset) {
		return PostgrePageHepler.getLimitString(sql,-1,-1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return PostgrePageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
