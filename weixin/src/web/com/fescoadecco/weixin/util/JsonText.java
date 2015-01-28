package com.fescoadecco.weixin.util;

import java.util.List;

import com.fescoadecco.weixin.dto.Articles;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonText {
	private String touser;
	public JsonText(String touser) {
		super();
		this.touser = touser;
	}

	public String getTextJson(String content) {
		JSONObject jsonObject=new JSONObject();
		JSONObject text=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "text");
		text.put("content", content);
		jsonObject.put("text", text);
		return jsonObject.toString();
	}

	public String getImageJson(String MEDIA_ID) {
		JSONObject jsonObject=new JSONObject();
		JSONObject image=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "image");
		image.put("media_id", MEDIA_ID);
		jsonObject.put("image", image);
		return jsonObject.toString();
	}

	public String getVoiceJson(String MEDIA_ID) {
		JSONObject jsonObject=new JSONObject();
		JSONObject voice=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "voice");
		voice.put("media_id", MEDIA_ID);
		jsonObject.put("voice", voice);
		return jsonObject.toString();
	}

	public String getVideoJson(String MEDIA_ID, String title, String description) {
		JSONObject jsonObject=new JSONObject();
		JSONObject video=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "video");
		video.put("media_id", MEDIA_ID);
		video.put("title", title);
		video.put("description", description);
		jsonObject.put("video", video);
		return jsonObject.toString();
	}

	public String getMusicJson(String title, String description,
			String musicurl, String hqmusicurl, String thumb_media_id) {
		JSONObject jsonObject=new JSONObject();
		JSONObject music=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "music");
		music.put("title", title);
		music.put("description", description);
		music.put("musicurl", musicurl);
		music.put("hqmusicurl", hqmusicurl);
		music.put("thumb_media_id", thumb_media_id);
		jsonObject.put("video", music);
		return jsonObject.toString();
	}
	public String getImgeTextJson(List<Articles> articles) {
		JSONArray ja=new JSONArray();
		ja.addAll(articles);
		JSONObject jsonObject=new JSONObject();
		JSONObject article=new JSONObject();
		jsonObject.put("touser", touser);
		jsonObject.put("msgtype", "news");
		article.put("articles", ja);
		jsonObject.put("news", article);
		return jsonObject.toString();
	}
	public static void main(String[] args) {
		//授权后test一条客服信息
		
	}
}
