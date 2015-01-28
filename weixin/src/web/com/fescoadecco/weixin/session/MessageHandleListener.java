package com.fescoadecco.weixin.session;

import com.fescoadecco.weixin.dto.msg.Msg4Event;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4Link;
import com.fescoadecco.weixin.dto.msg.Msg4Location;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;

public abstract interface MessageHandleListener {
	public abstract void onTextMsg(Msg4Text paramMsg4Text) throws Exception;

	public abstract void onImageMsg(Msg4Image paramMsg4Image) throws Exception;

	public abstract void onEventMsg(Msg4Event paramMsg4Event) throws Exception;

	public abstract void onLinkMsg(Msg4Link paramMsg4Link) throws Exception;

	public abstract void onLocationMsg(Msg4Location paramMsg4Location)
			throws Exception;

	public abstract void onVoiceMsg(Msg4Voice paramMsg4Voice) throws Exception;

	public abstract void onErrorMsg(int paramInt) throws Exception;

	public abstract void onVideoMsg(Msg4Video paramMsg4Video) throws Exception;
}
