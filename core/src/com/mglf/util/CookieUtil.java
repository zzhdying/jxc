package com.mglf.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	
	/**
	 * 获取cookie值
	 * @author zhongzhuohan
	 * @param request
	 * @param key
	 * @return
	 */
	public static String readCookie(HttpServletRequest request,String key){
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if (key.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		//如果cookieValue为空,返回,
		return cookieValue;
	}
}
