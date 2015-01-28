package com.mglf.dao.datasource;

/**
 * 动态调用数据库工具类
 * @author zhongzhuohan
 *
 */
public class DynamicDataSourceHolder {
	
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSource(String name) {
		holder.set(name);
	}

	public static String getDataSouce() {
		return holder.get();
	}
}