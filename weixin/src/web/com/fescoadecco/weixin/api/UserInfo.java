package com.fescoadecco.weixin.api;

import net.sf.json.JSONObject;

import com.mglf.util.StringUtil;
import com.fescoadecco.weixin.dto.AccessTokenResponse;
import com.fescoadecco.weixin.dto.UserResponse;
import com.fescoadecco.weixin.util.https.HttpsUtil;

public class UserInfo {
	/**
	 * 获取用户信息
	 * 
	 * @param openid
	 *            用户ID
	 * @return
	 */
	public static UserResponse userBase(String openid) {
		String url = Const.USERINFO_URL + "?access_token="
				+ Token.getAccessToken() + "&openid=" + openid + "&lang=zh_CN";
		String userStr = HttpsUtil.httpRequest(url, HttpsUtil.GET, null);
		return (UserResponse) JSONObject.toBean(JSONObject.fromObject(userStr),
				UserResponse.class);
	}
	/**
	 * 弹出页面授权方式，即使不关注也可以获取获取用户信息
	 * @param at
	 * @return
	 */
	public static UserResponse userInfo(AccessTokenResponse at) {
		// 获取用户信息
		String userUrl = Const.SNSAPI_USERINFO_URL + "?access_token="
				+ at.getAccess_token() + "&openid=" + at.getOpenid()
				+ "&lang=zh_CN";
		String userStr = HttpsUtil.httpRequest(userUrl, HttpsUtil.GET, null);
		return (UserResponse) JSONObject.toBean(JSONObject.fromObject(userStr),
				UserResponse.class);
	}
	
	
	/**
	 * 获取用户信息
	 * 
	 * @param code
	 * @return
	 */
	public static UserResponse getUserInfoByCode(String code, String type) {
		AccessTokenResponse at = Token.getAccessToken(code);
		if(StringUtil.isNotBlank(at.getOpenid())){
			UserResponse ur = new UserResponse();
			ur.setOpenid(at.getOpenid());
			return ur;
		}
		if (Const.SNSAPI_USERINFO.equals(type)
				|| Const.SNSAPI_USERINFO.equals(at.getScope())) {
			return userInfo(at);
		} else {
			try {//优先获取用户其他信息，获取不到则只返回openid
				return userBase(at.getOpenid());
			} catch (Exception e) {//
				UserResponse user=new UserResponse();
				user.setOpenid(at.getOpenid());
				return user;
			}
		}
		
	}
	
}
