package com.mglf.biz.constants;

public class FtlConstants {
	/**
	 *注册邮件激活
	 */
	final static public String REG_MAIL ="activeUserRegister_email";
	/**
	 *注册手机激活
	 */
	final static public String REG_MOBILE ="activeUserRegister_sms";
	
	/**
	 * 激活成功短信
	 */
	final static public String REG_SUCCESS_SMS = "activePerUserSuccess";
	/**
	 * 激活成功邮件
	 */
	final static public String REG_SUCCESS_EMAIL = "activePerUserSuccess_email";
	/**
	 * 激活成功站内信
	 */
	final static public String REG_SUCCESS_MESSAGE = "activePerUserSuccess_message";
	
	/**
	 * 添加好友邮件
	 */
	final static public String ADD_FREIND_ACTIVE_EMAIL = "activeRecommendedUser_email";
	/**
	 * 添加好友SMS
	 */
	final static public String ADD_FREIND_ACTIVE_SMS = "activeRecommendedUser_sms";
	/**
	 * 添加好友message
	 */
	final static public String ADD_FREIND_ACTIVE_MESSAGE= "activeRecommendedUser_message";
	
	/**
	 * 添加好友邮件
	 */
	final static public String ADD_FREIND_EXISTS_EMAIL = "activeExistsUser_email";
	/**
	 * 添加好友SMS
	 */
	final static public String ADD_FREIND_EXISTS_SMS = "activeExistsUser_sms";
	/**
	 * 添加好友message
	 */
	final static public String ADD_FREIND_EXISTS_MESSAGE= "activeExistsUser_message";
	
	/**
	 * 推荐职位短信
	 */
	final static public String RECOMMEND_JOB_SMS = "recommendJob_sms";
	/**
	 * 推荐职位站内信
	 */
	final static public String RECOMMEND_JOB_MESSAGE = "recommendJob_message";
	/**
	 * 推荐职位邮件
	 */
	final static public String RECOMMEND_JOB_EMAIL = "recommendJob_email";
	
	/**
	 * 推荐职位SUC邮件
	 */
	final static public String ACCEPT_RECOMMEND_JOB_EMAIL = "recommendAccept_email";
	/**
	 * 推荐职位SUCSMS
	 */
	final static public String ACCEPT_RECOMMEND_JOB_SMS = "recommendAccept_sms";
	/**
	 * 推荐职位SUC msg
	 */
	final static public String ACCEPT_RECOMMEND_JOB_MESSAGE= "recommendAccept_message";
	
	
	/**
	 * 找回密码邮件
	 */
	final static public String FIND_PASSWORD_EMAIL = "findPassword_email";
	/**
	 * 找回密码短信
	 */
	final static public String FIND_PASSWORD_SMS = "findPassword_sms";
	
	/**
	 * 改绑手机号前向原手机发送确认信息
	 */
	final static public String CHANGE_MOBILE_CONFIRM = "changeMobileConfirm_sms";
	
	/**
	 * 绑定新手机是给新手机发送确认信息 
	 */
	final static public String CONFIRM_NEW_MOBILE = "confirmNewMobile_sms";
	
	/**
	 * 改绑电子邮箱前向原邮箱发送确认信息
	 */
	final static public String CHANGE_EMAIL_CONFIRM = "changeEmailConfirm_email";
	
	/**
	 * 改绑电子邮箱前向新邮箱发送确认信息
	 */
	final static public String CONF_NEW_EMAIL = "confirmNewEmail_email";
	
	/**
	 * 绑定手机时发送认证码
	 */
	final static public String ACTIVE_BINDING_MOBILE = "activeBindingMobile_sms";
	
	/**
	 * 绑定邮箱时发送确认邮件
	 */
	final static public String ACTIVE_BINDING_EMAIL = "activeBindingEmail_email";
	
	
	/**
	 * 绑定支付宝时发送确认码
	 */
	final static public String CONF_BIND_ALIPAY = "confirmBindingAlipay_sms";
	
	
	/**
	 * 修改密码成功短信
	 */
	final static public String EDIT_PASSWORD_SUCCESS_SMS = "editPasswordSuccess_sms";
	
	final static public String EDIT_PASSWORD_SUCCESS_EMAIL = "editPasswordSuccess_email";
	
	final static public String EDIT_PASSWORD_SUCCESS_MESSAGE = "editPasswordSuccess_message";
	
	/**
	 * 确认面试
	 */
	final static public String CONFIRM_INTERVIEW_SMS="confirmIntegerView_sms";
	
	final static public String CONFIRM_INTERVIEW_EMAIL="confirmIntegerView_email";
	
	final static public String CONFIRM_INTERVIEW_MESSAGE="confirmIntegerView_message";
	
	
	/**
	 * 拒绝面试邀请
	 */
	final static public String REJECT_INTERVIEW_SMS="rejectIntegerView_sms";
	
	final static public String REJECT_INTERVIEW_EMAIL="rejectIntegerView_email";
	
	final static public String REJECT_INTERVIEW_MESSAGE="rejectIntegerView_message";
	
	
	/**
	 * 确认offer
	 */
	final static public String CONFIRM_OFFER_SMS="confirmOffer_sms";
	
	final static public String CONFIRM_OFFER_EMAIL="confirmOffer_email";
	
	final static public String CONFIRM_OFFER_MESSAGE="confirmOffer_message";
	
	
	/**
	 * 拒绝offer
	 */
	final static public String REJECT_OFFER_SMS="rejectOffer_sms";
	
	final static public String REJECT_OFFER_EMAIL="rejectOffer_email";
	
	final static public String REJECT_OFFER_MESSAGE="rejectOffer_message"; 
	
	
	/**
	 * 拒绝推荐职位SUC邮件
	 */
	final static public String REFUSE_RECOMMEND_JOB_EMAIL = "recommendRefuse_email";
	/**
	 * 拒绝推荐职位SUCSMS
	 */
	final static public String REFUSE_RECOMMEND_JOB_SMS = "recommendRefuse_sms";
	/**
	 * 拒绝推荐职位SUC msg
	 */
	final static public String REFUSE_RECOMMEND_JOB_MESSAGE= "recommendRefuse_message";
	
	
	/**
	 * 个人应聘职位向HR发送站内信
	 */
	final static public String PER_APP_JOB_TO_HR_MESSAGE = "perAppJobToHr_message";
	final static public String PER_APP_JOB_TO_HR_EMAIL = "perAppJobToHr_email";
	
	
	/**
	 * 个人撤销应聘向HR发送通知
	 */
	final static public String PER_CANCEL_APP_TO_HR_MESSAGE = "cancelApp_message";
	final static public String PER_CANCEL_APP_TO_HR_EMAIL = "cancelApp_email";
	
	/**
	 * 个人用户简易简历发送通知
	 */
	final static public String PER_FIRST_RESUME_SIMPLE_SMS = "first_resume_simple_sms";
	
	
	/**
	 * 个人用户完善简历向HR发送通知
	 */
	final static public String PER_IMPROVE_RESUME_TO_HR_MESSAGE = "improve_resume_to_hr_message";
	final static public String PER_IMPROVE_RESUME_TO_HR_EMAIL = "improve_resume_to_hr_email";
	
	
	/*********************************************************
	*
	*发送标题
	*********************************************************/
	
	/**
	 * 激活站内信/邮件 subject
	 */
	final static public String REG_MESSAGE_SUBJECT = "注册激活";
	/**
	 * 激活成功站内信/邮件 subject
	 */
	final static public String REG_SUCCESS_MESSAGE_SUBJECT = "欢迎您";
	/**
	 * 添加好友站内信/邮件 subject
	 */
	final static public String ADD_FREIND_ACTIVE_SUBJECT = "新的好友";
	/**
	 * 推荐职位站内信/邮件 subject
	 */
	final static public String RECOMMEND_JOB_SUBJECT = "职位推荐";
	/**
	 * 接受推荐职位站内信/邮件 subject
	 */
	final static public String ACCEPT_RECOMMEND_JOB_SUBJECT = "好友接受推荐通知";
	/**
	 * 拒绝推荐职位站内信/邮件 subject
	 */
	final static public String REFUSE_RECOMMEND_JOB_SUBJECT = "好友拒绝推荐通知";
	/**
	 * 找回密码标题
	 */
	final static public String FIND_PASSWORD_SUBJECT = "找回密码";
	
	/**
	 * 激活站内信/邮件 subject
	 */
	final static public String CHANGE_EMAIL_CONFIRM_SUBJECT = "邮箱改绑";
	
	/**
	 * 激活站内信/邮件 subject
	 */
	final static public String CONF_NEW_EMAIL_SUBJECT = "绑定新邮箱";
	
	/**
	 * 绑定个人邮箱
	 */
	final static public String ACTIVE_BINDING_EMAIL_SUBJECT = "绑定个人邮箱";
	
	/**
	 * 密码修改标题
	 */
	final static public String EDIT_PASSWORD_SUBJECT = "密码修改";
	
	
	/**
	 * 个人撤销应聘标题
	 */
	final static public String CANCEL_APP = "个人用户撤销应聘";
 
}
