package com.fescoadecco.weixin.session;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import com.mglf.dto.PageForJqGrid;
import com.mglf.util.ConfigUtil;
import com.fescoadecco.weixin.api.Const;
import com.fescoadecco.weixin.dto.msg.Data4Item;
import com.fescoadecco.weixin.dto.msg.Msg4Event;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4Link;
import com.fescoadecco.weixin.dto.msg.Msg4Location;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;
import com.fescoadecco.weixin.util.ImageUtil;
import com.fescoadecco.weixin.util.StringUtil;

public class BlueMessageHandle extends MessageHandleAdapter {

	public static String host = ConfigUtil.readSysValue("perWebUrl") + "/" + ConfigUtil.readSysValue("BluePersonWeb");
	
	public static String mobileIndexUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.APPID+"&redirect_uri="+host+"/mobile/weixinclientlogin&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	
	private DefaultSession session;
	
	
	public BlueMessageHandle(DefaultSession session){
		this.session =session;
	}
	
	@Override
	public void onTextMsg(Msg4Text msg) throws Exception {
		if("二维码".equals(msg.getContent())){
			//JSONObject imgObj = ImageUtil.send("image", "D:\\workspace\\BluePersonWeiXin\\WebContent\\static\\images\\2wm.jpg");
			String wxhost = ConfigUtil.readWeixinValue("wexinUrl") + ConfigUtil.readSysValue("BluePersonWeixin");
			JSONObject imgObj = ImageUtil.send("image", wxhost + "/static/images/2wm.jpg");
			if(!imgObj.containsKey("errcode")){
				String mkey = imgObj.getString("media_id");
				
			}else{
				List<Data4Item> list = new ArrayList<Data4Item>();

				Data4Item di = new Data4Item();
				di.setTitle("扫描二维码");
				di.setDescription("");
				di.setPicUrl("http://10.11.4.33/perweixin/static/images/2wm.jpg");
				list.add(di);

				MsgPassiveUtil.createImageText(msg, list, session);
			}
			return ;
		}
	}

	/**
	 * 获取图片消息
	 */
	@Override
	public void onImageMsg(Msg4Image paramMsg4Image) {
		// TODO Auto-generated method stub

	}

	
	private void processGetLatestJobEvent(Msg4Event paramMsg4Event)  throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		PageForJqGrid<Map<String, Object>> page = new PageForJqGrid<Map<String,Object>>();
		// 每次取10条
		page.setRows(5);
		// 只取第一页
		page.setPage(1);
		condition = page.pageToMap(page, condition);
		// 以审核时间为排序
		condition.put("sidx", "REFRESH_DATETIME");
		condition.put("sord", "DESC");
		
	}
	
	
	/**
	 * 获取事件消息（关注/取消关注）
	 */
	@Override
	public void onEventMsg(Msg4Event paramMsg4Event)  throws Exception {
		// 根据不同的消息类型组建msg
		if (Msg4Event.SUBSCRIBE.equals(paramMsg4Event.getEvent())) {// 订阅
			if (paramMsg4Event.getTicket() != null) {// 用户未关注时，进行关注后的事件推送
				MsgPassiveUtil.createText("欢迎关注一起推，我们全体运营团队将不断完善服务，提升体验，也希望您通过这个窗口为我们提出意见反馈。\n在这里您可以进行职位搜索，接受订阅职位，查看应聘和推荐的进程。\n祝您通过一起推找到合适的工作-------一起推运营团队\n<a href='"+mobileIndexUrl+"'>eachtui.com</a>", paramMsg4Event,
						session);
			} else {// 其他方式订阅
				MsgPassiveUtil.createText("欢迎关注一起推，我们全体运营团队将不断完善服务，提升体验，也希望您通过这个窗口为我们提出意见反馈。\n在这里您可以进行职位搜索，接受订阅职位，查看应聘和推荐的进程。\n祝您通过一起推找到合适的工作-------一起推运营团队\n<a href='"+mobileIndexUrl+"'>eachtui.com</a>", paramMsg4Event,
						session);
			}

		} else if (Msg4Event.UNSUBSCRIBE.equals(paramMsg4Event
				.getEvent())) {// 取消订阅
			MsgPassiveUtil.createText("取消订阅", paramMsg4Event, session);
		} else if (Msg4Event.CLICK.equals(paramMsg4Event.getEvent())) {// 点击菜单拉取消息时的事件推送
			if (Const.GET_LATEST_JOB.equals(paramMsg4Event.getEventKey())) { 
				processGetLatestJobEvent(paramMsg4Event);				
			}
		} else if (Msg4Event.VIEW.equals(paramMsg4Event.getEvent())) {// 点击菜单跳转链接时的事件推送
			//
		} else if (Msg4Event.LOCATION.equals(paramMsg4Event
				.getEvent())) {// 上报地理位置事件
			//
		} else if (Msg4Event.SCAN.equals(paramMsg4Event.getEvent())) {// 用户已关注时的事件推送
			MsgPassiveUtil.createText("二维码扫描", paramMsg4Event, session);
		}

	}

	/**
	 * 链接消息
	 */
	@Override
	public void onLinkMsg(Msg4Link paramMsg4Link)  throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 地理位置消息
	 */
	@Override
	public void onLocationMsg(Msg4Location paramMsg4Location) {
		// TODO Auto-generated method stub

	}

	/**
	 * 语音消息
	 */
	@Override
	public void onVoiceMsg(Msg4Voice paramMsg4Voice) {
		// TODO Auto-generated method stub

	}

	/**
	 * 异常消息
	 */
	@Override
	public void onErrorMsg(int paramInt) {

	}

	/**
	 * 视频消息
	 */
	@Override
	public void onVideoMsg(Msg4Video paramMsg4Video) {
		// TODO Auto-generated method stub

	}

}
