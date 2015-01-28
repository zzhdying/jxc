
package com.mglf.entity;

import java.util.Date;

import com.mglf.base.BaseEntity;

/**
 * 用户访问日志类
 * 
 * @author zhuben
 * 
 */
public class UserAccess  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int userType;
	private String userId;
	private int operType;
	private String operIp;
	private Date operDatetime;
	
	/**
	 * 后台用户
	 */
	public static final int USER_TYPE_ADMIN = 1;
	
	/**
	 * 企业用户
	 */
	public static final int USER_TYPE_ENT = 2;
	
	/**
	 * 个人用户
	 */
	public static final int USER_TYPE_PER = 3;
	

	
	/**
	 * 操作类型
	 */
	public static final int OPER_TYPE_WEB_LOGIN = 1;
	public static final int OPER_TYPE_MOBILE_BROWSE_LOGIN = 2;
	public static final int OPER_TYPE_MOBILE_WEIXIN_LOGIN = 3;
	
	public static final int OPER_TYPE_APPLICANT = 4;
	public static final int OPER_TYPE_IMPORTRESUME_51 = 5;
	public static final int OPER_TYPE_IMPORTRESUME_ZHILIAN = 6;
	public static final int OPER_TYPE_IMPORTRESUME_LIEPIN = 7;
	public static final int OPER_TYPE_IMPORTRESUME_LINKEDIN = 8;
	public static final int OPER_TYPE_MANUALRESUME = 9;
	public static final int OPER_TYPE_SEND_RESUME = 10;


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public int getUserType() {
		return userType;
	}



	public void setUserType(int userType) {
		this.userType = userType;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public int getOperType() {
		return operType;
	}



	public void setOperType(int operType) {
		this.operType = operType;
	}



	public String getOperIp() {
		return operIp;
	}



	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}



	public Date getOperDatetime() {
		return operDatetime;
	}



	public void setOperDatetime(Date operDatetime) {
		this.operDatetime = operDatetime;
	}
	
	
	

}
