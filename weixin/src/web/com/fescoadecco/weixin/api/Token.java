package com.fescoadecco.weixin.api;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.fescoadecco.weixin.dto.AccessTokenResponse;
import com.fescoadecco.weixin.util.https.HttpsUtil;

public class Token {

	public static Long token_out = 0L;

	/**
	 * 获取基础token
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		try {
			String token = (String) CacheUtil.load(CacheUtil.GROUP_WEIXIN_KEY,
					"token");
			if (token != null) {
				return token;
			}

			StringBuffer sb = new StringBuffer();
			sb.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
			sb.append("&appid=");
			sb.append(ConfigUtil.readWeixinValue("appid"));
			sb.append("&secret=");
			sb.append(ConfigUtil.readWeixinValue("secret"));

			String url = sb.toString();
			String resp = "";

			HttpClient client = new HttpClient();
			GetMethod getMethod = new GetMethod(url);

			int statusCode = client.executeMethod(getMethod);

			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("getToken fail!");
			}

			resp = getMethod.getResponseBodyAsString();

			getMethod.releaseConnection();

			JSONObject json = JSONObject.fromObject(resp);

			token = json.getString("access_token");

			CacheUtil.save(CacheUtil.GROUP_WEIXIN_KEY, "token", token);
			return token;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getAccessToken());
	}

	/**
	 * 获取用户信息时需要用到token
	 * 
	 * @param code
	 * @return
	 */
	public static AccessTokenResponse getAccessToken(String code) {
		String url = Const.GETACCESS_TOKEN + "?appid=" + Const.APPID
				+ "&secret=" + Const.SECRET + "&code=" + code
				+ "&grant_type=authorization_code";
		String jsonStr = HttpsUtil.httpRequest(url, HttpsUtil.GET, null);
		JSONObject jo = JSONObject.fromObject(jsonStr);
		AccessTokenResponse au = (AccessTokenResponse) JSONObject.toBean(jo,
				AccessTokenResponse.class);
		return au;
	}
}
