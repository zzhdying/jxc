package com.fescoadecco.weixin.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.fescoadecco.weixin.dto.Menu;
import com.fescoadecco.weixin.dto.StateCode;
import com.fescoadecco.weixin.util.https.HttpsUtil;

public class MenuManager {
	/**
	 * 菜单创建
	 * @param m 菜单本体
	 */
	public static StateCode createMenu(List<Menu> m) {
		String url=Const.CREATE_MENU+"?access_token="+Token.getAccessToken();
		
		JSONArray j=new JSONArray();
		j.addAll(m);
		
		JSONObject menu = new JSONObject();
		menu.put("button", j);
		
		String msg=HttpsUtil.httpRequest(url, HttpsUtil.POST, menu.toString());
		return (StateCode) JSONObject.toBean(JSONObject.fromObject(msg), StateCode.class);
	}
	/**
	 * 菜单获取
	 * @param m 菜单本体
	 */
	public static void getMenu() {
		String url=Const.GET_MENU+"?access_token="+Token.getAccessToken();
		HttpsUtil.httpRequest(url, HttpsUtil.GET, null);
		
	}
	/**
	 * 菜单删除
	 * @param m 菜单本体
	 */
	public static void delMenu() {
		try {
			CacheUtil.delete(CacheUtil.GROUP_WEIXIN_KEY,
					"token");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url=Const.DEL_MENU+"?access_token="+Token.getAccessToken();
		HttpsUtil.httpRequest(url, HttpsUtil.GET,null);
		
	}
	public static void main(String[] args) {		
		//String host = ConfigUtil.readSysValue("perWebUrl") + "/" + ConfigUtil.readSysValue("BluePersonWeb");
		String host = "http://z.eachtui.com/p";
		MenuManager.delMenu();
		List<Menu> ms=new ArrayList<Menu>();
		Menu m=new Menu("获取最新",Menu.CLICK,Const.GET_LATEST_JOB);
		//Menu m=new Menu("获取最新",Menu.VIEW,"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri="+host+"/mobile/weixinclientsearch&response_type=code&scope=snsapi_base&state=state#wechat_redirect");
		Menu m1=new Menu("应聘进程",Menu.VIEW,"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri="+host+"/mobile/weixinclientrefapp&response_type=code&scope=snsapi_base&state=state#wechat_redirect");
		Menu m2=new Menu("首页",Menu.VIEW,"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri="+host+"/mobile/weixinclientlogin&response_type=code&scope=snsapi_base&state=state#wechat_redirect");
		ms.add(m);
		ms.add(m1);
		ms.add(m2);
		//JSONObject jo=m.tojson();
//		List<Menu> mlist=new ArrayList<>();
//		mlist.add(new Menu("", "1", "click"));
//		m.setSubButton(mlist);
		//System.out.println(jo.toString());
		StateCode s=MenuManager.createMenu(ms);
		System.out.println(s.getErrmsg()+s.getErrcode());
	}
}
