package com.mglf.biz.constants;

/**
 * 常量类
 * @author jinxiaochen
 *
 */
public class Constants {
	
	/**
	 * 手机号正则
	 * @author jinxiaochen
	 */
	final static public String MOBILE_REG = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
	
	/**
	 * 邮箱正则
	 * @author jinxiaochen
	 */
	final static public String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	
	/**
	 * 上一个短地址存放SESSION的key
	 * @author jinxiaochen
	 */
	final static public String LOGINFROMSHARE_SHORTPATH_SESSION = "SHORTPATH_SESSION";
	
}
