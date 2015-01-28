package com.fescoadecco.weixin.api;

import com.mglf.util.ConfigUtil;

public class Const {
	public final static Long TOKEN_OUT_TIME = 7200L;
	
	//public final static String APPID = "wx3ec9e57674480a6d";"wxcc1442e6282cdebf"
	//public final static String SECRET = "2c9fe182cd24185e79f7a2d041d23420";"9dd7acf84f41e742d6361fd1362ad341"
	public final static String SECRET = ConfigUtil.readWeixinValue("secret");
	public final static String APPID = ConfigUtil.readWeixinValue("appid") ;
	/**
	 * 获取二维码ticket
	 */
	public final static String QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	/**
	 * 获取token
	 */
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	/**
	 * 获取二维码image
	 */
	public final static String SHOWQRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

	public final static String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	
	public final static String SNSAPI_USERINFO_URL="https://api.weixin.qq.com/sns/userinfo";
	/**
	 * 获取授权token，与基础token不同
	 */
	public static final String GETACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	/**
	 * 创建菜单
	 */
	public final static String CREATE_MENU="https://api.weixin.qq.com/cgi-bin/menu/create";
	/**
	 * 获取菜单
	 */
	public final static String GET_MENU="https://api.weixin.qq.com/cgi-bin/menu/get";
	/**
	 * 删除菜单
	 */
	public final static String DEL_MENU="https://api.weixin.qq.com/cgi-bin/menu/delete";
	/**
	 * 推送客服消息
	 */
	public final static String SENDMSG="https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	public final static String SNSAPI_USERINFO="snsapi_userinfo";
	public final static String SNSAPI_BASE="snsapi_base";
	
	/**
	 * 获取最新职位按钮KEY
	 * */
	public final static String GET_LATEST_JOB = "GET_LATEST_JOB";
}
