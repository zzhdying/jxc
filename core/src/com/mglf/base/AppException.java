package com.mglf.base;

/**
 * 应用层异常
 * @author zhongzhuohan
 *
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 异常信息
	 */
	private String msg;
	
	
	/**
	 * construct
	 */
	public AppException() {
		this.msg = null;
	}

	/**
	 * construct
	 */
	public AppException(Exception e) {
		this.msg = e.toString();
	}
	
	/**
	 * construct
	 */
	public AppException(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取异常信息
	 * @return 异常信息
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置异常信息
	 * @param msg 异常信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
