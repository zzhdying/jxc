package com.fescoadecco.weixin.dto;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 菜单Bean 供转换json用
 * @author xpc
 *
 */
public class Menu {
  public static final String CLICK = "click";
  public static final String VIEW = "view";
	private String name;
	private String type;
	private String key;
	private List<Menu> subButton;//二级菜单
	private String url;
	
	public Menu() {
		
	}
	
	public Menu(String name, String type, String key) {
		super();
		this.name = name;
		this.type = type;
		if (VIEW.equals(type)) {
			this.url = key;
		}else {
			this.key = key;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public List<Menu> getSubButton() {
		return subButton;
	}

	public void setSubButton(List<Menu> subButton) {
		this.subButton = subButton;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private JSONObject fromBean(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", getName());
		if (getType()!=null)
			jsonObject.put("type", getType());
		if (getKey()!=null) 
			jsonObject.put("key", getKey());
		if (getUrl()!=null) 
			jsonObject.put("url", getUrl());
		
		return jsonObject;
	}
	public String tojson(){
		JSONObject jsonObject=fromBean();
		if (getSubButton()!=null) {
			JSONArray ja=new JSONArray();
			for (Menu m : subButton) {
				ja.add(m);
			}
			jsonObject.put("sub_button", ja);
		}
		return jsonObject.toString();
	}
	
}
