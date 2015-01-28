package com.fescoadecco.weixin.util;

import java.util.Iterator;

import net.sf.json.JSONObject;

import com.mglf.util.ConfigUtil;
import com.fescoadecco.weixin.api.Const;

public class StringUtil {
	
	public static String getUrl(String viewName,String param){
		String host = ConfigUtil.readSysValue("perWebUrl")+ "/" + ConfigUtil.readSysValue("BluePersonWeb");
		//Iterator keySet = param.keySet().iterator();
		//String paramStr =  param.toString();
		
		
		
		 //String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri=http://"+host+"/per/mobile/weiXinClientLogin&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
		String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s/%s&response_type=code&scope=snsapi_base&state=%s#wechat_redirect", 
				 Const.APPID,host,viewName,param);
		return url;
	}
}
