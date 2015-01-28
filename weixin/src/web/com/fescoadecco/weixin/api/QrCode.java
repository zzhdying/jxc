package com.fescoadecco.weixin.api;

import net.sf.json.JSONObject;

import com.fescoadecco.weixin.util.WXXmlElementName;
import com.fescoadecco.weixin.util.https.HttpsUtil;

public class QrCode {
	/**
	 * 推广用 二维码，
	 * 
	 * @param token
	 * @param type
	 *            二维码类型 永久QR_LIMIT_SCENE 临时QR_SCENE
	 * @param seconds
	 *            过期时间 最长为1800
	 * @return ticket 票据 用来换取地址
	 */
	public static String getQrCode(String type, Integer seconds) {
		
		String url = Const.QRCODE_URL + "?access_token=" +  Token.getAccessToken();
		JSONObject jsonObject = new JSONObject();
		JSONObject scene=new JSONObject();
		
		scene.put("scene_id","123");
		
		jsonObject.put("action_info", scene);
		
		if (type == null) {
			type = WXXmlElementName.QR_SCENE;
		}
		if (WXXmlElementName.QR_LIMIT_SCENE.equals(type)) {
			jsonObject.put("action_name", "QR_SCENE");
		}else {
			jsonObject.put("action_name", "QR_LIMIT_SCENE");
			jsonObject.put("expire_seconds", seconds);
		}
		String reMsg = HttpsUtil.httpRequest(url, HttpsUtil.POST, jsonObject.toString());
		JSONObject ticketJson = JSONObject.fromObject(reMsg);
		String ticket = (String) ticketJson.get("ticket");
		return ticket;
	}

	/**
	 * 根据票据获取二维码 HTTP头（示例）如下： Accept-Ranges:bytes Cache-control:max-age=604800
	 * Connection:keep-alive Content-Length:28026 Content-Type:image/jpg
	 * Date:Wed, 16 Oct 2013 06:37:10 GMT Expires:Wed, 23 Oct 2013 14:37:10
	 * +0800 Server:nginx/1.4.1
	 * 
	 * @param ticket
	 * @return
	 */
	public static String getQrByTicket(String ticket) {
		String url = Const.SHOWQRCODE_URL + "?ticket=" + ticket;
		String reMsg = HttpsUtil.httpRequest(url, HttpsUtil.GET, null);
		return reMsg;
	}
}
