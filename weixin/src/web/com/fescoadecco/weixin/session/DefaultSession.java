package com.fescoadecco.weixin.session;

import java.util.ArrayList;
import java.util.List;

import com.fescoadecco.weixin.dto.msg.Msg4Event;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4Link;
import com.fescoadecco.weixin.dto.msg.Msg4Location;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;


public class DefaultSession extends Session {
	private List<MessageHandleListener> listeners = new ArrayList<MessageHandleListener>(3);

	public static DefaultSession newInstance() {
		return new DefaultSession();
	}

	public void addOnHandleMessageListener(MessageHandleListener handleMassge) {
		this.listeners.add(handleMassge);
	}

	public void removeOnHandleMessageListener(MessageHandleListener handleMassge) {
		this.listeners.remove(handleMassge);
	}

	public void onTextMsg(Msg4Text msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onTextMsg(msg);
	}

	public void onImageMsg(Msg4Image msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onImageMsg(msg);
	}

	public void onEventMsg(Msg4Event msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onEventMsg(msg);
	}

	public void onLinkMsg(Msg4Link msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onLinkMsg(msg);
	}

	public void onLocationMsg(Msg4Location msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onLocationMsg(msg);
	}

	public void onErrorMsg(int errorCode)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onErrorMsg(errorCode);
	}

	public void onVoiceMsg(Msg4Voice msg)  throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onVoiceMsg(msg);
	}

	public void onVideoMsg(Msg4Video msg) throws Exception {
		for (MessageHandleListener currentListener : this.listeners)
			currentListener.onVideoMsg(msg);
	}
}
