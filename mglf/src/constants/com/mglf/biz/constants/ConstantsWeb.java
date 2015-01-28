package com.mglf.biz.constants; 
public class ConstantsWeb {
	/**
	 * 登陆消息
	 */
	public static final String LOGIN_SUCCESS="0000";
	public static final String LOGIN_FAIL_NOUSERNAME="9001";
	public static final String LOGIN_FAIL_NOUSERNAME_MSG="用户名不能为空";
	public static final String LOGIN_FAIL_NOUPASSWORD="9002";
	public static final String LOGIN_FAIL_NOUPASSWORD_MSG="用户密码不能为空";
	public static final String LOGIN_FAIL_NOTACTIVE="9011";
	public static final String LOGIN_FAIL_NOTACTIVE_MSG="用户未激活";
	public static final String LOGIN_FAIL_WRONGPASSWORD ="9012";
	public static final String LOGIN_FAIL_WRONGPASSWORD_MSG="错误的用户名密码";
	public static final String LOGIN_FAIL_NOUSER ="9013";
	public static final String LOGIN_FAIL_NOUSER_MSG="错误的用户名密码";
	/**
	 * 注册消息
	 */
	public static final String REGISTER_SUCCESS_MOBILE="0000";
	public static final String REGISTER_SUCCESS_EMAIL="0001";
	public static final String REGISTER_ERROR_PASSWORDCONFIRMERROR="9001";
	public static final String REGISTER_ERROR_PASSWORDCONFIRMERROR_MSG="两次输入的密码有误";
	public static final String REGISTER_ERROR_USERNAMEERROR="9002";
	public static final String REGISTER_ERROR_USERNAMEERROR_MSG="用户名格式错误";
	public static final String REGISTER_ERROR_USERNAMEEXISTS="9003";
	public static final String REGISTER_ERROR_USERNAMEEXISTS_MSG="用户名已存在";
	public static final String REGISTER_ACTIVEMAIL_SUCCESS="0002";
	public static final String REGISTER_ACTIVEMAIL_ERROR="1002";
	public static final String REGISTER_ACTIVEMAIL_ERROR_MSG="激活失败，请稍后再试";
	public static final String REGISTER_ACTIVEMAIL_TIMEOUT_MSG="信息已超时，请重新获取激活邮件";
	public static final String REGISTER_ACTIVMOBILE_SUCCESS="0003";
	public static final String REGISTER_ACTIVMOBILE_ERROR="1003";
	public static final String REGISTER_ACTIVMOBILE_ERROR_MSG="激活失败，请检查验证码是否正确";
	public static final String REGISTER_ACTIVMOBILE_TIMEOUT_MSG="信息已超时,请重新获取验证码";
	public static final String REGISTER_ACTIVE_LOGINTIMEOUT="用户信息失效，请重新登录";
	/**
	 * AJAX返回状态
	 */
	public static final String REGISTER_HASREGISTER="2001";
	public static final String REGISTER_HASREGISTER_MSG="用户已注册";
	
	
	
	public static final String REGISTER_RESENDMOBILE_SUCCESS="0004";
	public static final String REGISTER_RESENDMOBILE_ERROR="1004";
	public static final String REGISTER_RESENDMOBILE_ERROR_MSG="发送失败，请稍后再试";
	public static final String REGISTER_RESENDEMAIL_SUCCESS="1";
	public static final String REGISTER_RESENDEMAIL_ERROR="0";
	public static final String REGISTER_RESENDEMIAL_ERROR_MSG="发送失败，请稍后再试";
	
	public static final String FRIENDS_ADD_SUCCESS="0000";
	public static final String FRIENDS_ADD_ERROR="9001";
	public static final String FRIENDS_ADD_ERROR_MSG="添加失败，请稍后再试";
	public static final String FRIENDS_ADD_EXISTS="9002";
	public static final String FRIENDS_ADD_EXISTS_MSG="已经是好友";
	
	public static final String RECOMMEND_ACCEPT_SUCCESS="0000";
	public static final String RECOMMEND_ACCEPT_SUCCESS_MSG="已接受推荐";
	public static final String RECOMMEND_fAIL_PARAMERROR="9000";
	public static final String RECOMMEND_fAIL_PARAMERROR_MSG="参数错误";
	public static final String RECOMMEND_NEEDPERFECT="1001"; 
	public static final String RECOMMEND_NEEDPERFECT_MSG="请完善个人信息"; 
	public static final String RECOMMEND_NEEDJD="1002";
	public static final String RECOMMEND_NEEDJD_MSG="请完善简历";
	public static final String RECOMMEND_MAXCOUNT="1003";
	public static final String RECOMMEND_MAXCOUNT_MSG="超过当天次数限制"; 
	public static final String RECOMMEND_NEEDBINDMOBILE="1004";
	public static final String RECOMMEND_NEEDBINDMOBILE_MSG="微信用户未绑定手机";

	public static final String RECOMMEND_NEEDACTIVE_MSG="9006";
	public static final String RECOMMEND_NEEDACTIVE="接受推荐用户未激活";
	public static final String RECOMMEND_NOLOGIN="2222";
	public static final String RECOMMEND_NOLOGIN_MSG="未登录用户";
	public static final String APPLYJOB_JOBCLOSEORNOTEXISTS="职位已关闭或不存在";
	
	public static final String RECOMMEND_SELF_HASAPPLYED="8001";
	public static final String RECOMMEND_SELF_HASAPPLYED_MSG="您已经应聘了该职位";
	
	public static final String USER_NOSESSIONUSER="2000";
	public static final String USER_NOSESSIONUSER_MSG="用户未登录或登陆超时";
	public static final String SELFAPPLICANT_JD_NONE="9001";
	public static final String SELFAPPLICANT_JD_NOTFRESH="9002";
	public static final String SELFAPPLICANT_JD_NOTFULLBUTFRESH="9003";
	public static final String SELFAPPLICANT_JD_NOTFRESHBUTFULL="9004";
	public static final String SELFAPPLICANT_JD_NOTFULLANDFRESH="9005";
	public static final String SELFAPPLICANT_JD_FULLANDFRESH="0000";
	
	/**
	 * 验证返回
	 * 
	 */
	public static final String LOGINUSER_EMIAL_NULL="3001";
	
	public static final String MOBILECHECK_ERROR="3002";
	public static final String MOBILECHECK_ERROR_MSG="手机验证码错误";
}
