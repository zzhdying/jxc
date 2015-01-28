package com.fescoadecco.weixin.session;

import java.util.List;

import com.fescoadecco.weixin.dto.msg.Data4Item;
import com.fescoadecco.weixin.dto.msg.Msg;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4ImageText;
import com.fescoadecco.weixin.dto.msg.Msg4Music;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;
import com.fescoadecco.weixin.session.DefaultSession;

/**
 * 创建被动返回消息
 * 
 * @author xpc
 * 
 */
public class MsgPassiveUtil {
	public enum msgType {
		MSG_TYPE_TEXT, MSG_TYPE_IMAGE, MSG_TYPE_MUSIC, MSG_TYPE_LOCATION, MSG_TYPE_LINK, MSG_TYPE_IMAGE_TEXT, MSG_TYPE_EVENT, MSG_TYPE_VOICE, MSG_TYPE_VIDEO
	}

	/**
	 * 创建被动式回复文本
	 * 
	 * @param content
	 *            内容
	 * @param msg
	 *            消息对象
	 * @param session
	 * @return
	 */
	public static Msg4Text createText(String content, Msg msg,
			DefaultSession session) throws Exception {
		Msg4Text msg4Text = new Msg4Text();
		msg4Text.setFromUserName(msg.getToUserName());
		msg4Text.setToUserName(msg.getFromUserName());
		msg4Text.setContent(content);
		session.callback(msg4Text);
		return msg4Text;
	}

	/**
	 * 创建被动式回复图片
	 * 
	 * @param mediaId
	 *            媒体ID
	 * @param msg
	 *            消息对象
	 * @param session
	 * @return
	 */
	public static Msg4Image createImage(String mediaId, Msg msg,
			DefaultSession session) throws Exception {
		Msg4Image msg4Image = new Msg4Image();
		msg4Image.setFromUserName(msg.getToUserName());
		msg4Image.setToUserName(msg.getFromUserName());
		msg4Image.setMediaId(mediaId);
		session.callback(msg4Image);
		return msg4Image;
	}

	/**
	 * 创建被动式回复语音
	 * 
	 * @param mediaId
	 *            媒体ID
	 * @param msg
	 *            消息ID
	 * @param session
	 * @return
	 */
	public static Msg4Voice createVoice(String mediaId, Msg msg,
			DefaultSession session) throws Exception {
		Msg4Voice msg4Voice = new Msg4Voice();
		msg4Voice.setFromUserName(msg.getToUserName());
		msg4Voice.setToUserName(msg.getFromUserName());
		msg4Voice.setMediaId(mediaId);
		session.callback(msg4Voice);
		return msg4Voice;
	}

	/**
	 * 创建被动式回复视频
	 * 
	 * @param mediaId
	 *            媒体ID
	 * @param title
	 *            标题
	 * @param descrition
	 *            描述
	 * @param msg
	 *            消息对象
	 * @param session
	 * @return
	 */
	public static Msg4Video createVidco(String mediaId, String title,
			String descrition, Msg msg, DefaultSession session)
			throws Exception {
		Msg4Video msg4Video = new Msg4Video();
		msg4Video.setFromUserName(msg.getToUserName());
		msg4Video.setToUserName(msg.getFromUserName());
		msg4Video.setMediaId(mediaId);
		msg4Video.setTitle(title);
		msg4Video.setDescription(descrition);
		session.callback(msg4Video);
		return msg4Video;
	}

	/**
	 * 创建被动式回复音乐
	 * 
	 * @param mediaId
	 *            媒体ID
	 * @param title
	 *            标题
	 * @param descrition
	 *            描述
	 * @param msg
	 *            消息对象
	 * @param musicUrl
	 *            歌曲链接
	 * @param hQMusicUrl
	 *            高清歌曲链接（wifi使用）
	 * @param thumbMediaId
	 *            缩略图的媒体id
	 * @param session
	 * @return
	 */
	public static Msg4Music createMusic(String mediaId, String title,
			String descrition, Msg msg, String musicUrl, String hQMusicUrl,
			String thumbMediaId, DefaultSession session) throws Exception {
		Msg4Music msg4Music = new Msg4Music();
		msg4Music.setFromUserName(msg.getToUserName());
		msg4Music.setToUserName(msg.getFromUserName());
		msg4Music.setMusicUrl(musicUrl);
		msg4Music.setTitle(title);
		msg4Music.setDescription(descrition);
		msg4Music.setHQMusicUrl(hQMusicUrl);
		msg4Music.setThumbMediaId(thumbMediaId);
		session.callback(msg4Music);
		return msg4Music;
	}

	/**
	 * 创建被动式图文回复
	 * 
	 * @param msg
	 *            消息对象
	 * @param itemList
	 *            图文对象
	 * @param session
	 * @return
	 */
	public static Msg4ImageText createImageText(Msg msg,
			List<Data4Item> itemList, DefaultSession session) throws Exception {
		Msg4ImageText mit = new Msg4ImageText();
		mit.setFromUserName(msg.getToUserName());
		mit.setToUserName(msg.getFromUserName());
		mit.setCreateTime(msg.getCreateTime());
		for (Data4Item data4Item : itemList) {
			mit.addItem(data4Item);
		}
		session.callback(mit);
		return mit;
	}

}
