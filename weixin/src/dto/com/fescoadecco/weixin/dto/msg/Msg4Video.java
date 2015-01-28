package com.fescoadecco.weixin.dto.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4Video extends Msg {
	private String mediaId;
	private String title;
	private String description;
	private String msgId;

	public Msg4Video() {
		this.head = new Msg4Head();
		this.head.setMsgType("video");
	}

	public Msg4Video(Msg4Head head) {
		this.head = head;
	}

	public void write(Document document) {
		Element root = document.createElement("xml");
		this.head.write(root, document);
		Element videoElement = document.createElement("Video");
		Element mediaIdElement = document.createElement("MediaId");
		Element titleElement = document.createElement("Title");
		Element description = document.createElement("Description");
		videoElement.appendChild(mediaIdElement);
		videoElement.appendChild(titleElement);
		videoElement.appendChild(description);
		root.appendChild(videoElement);
		document.appendChild(root);
	}

	public void read(Document document) {
		this.mediaId = getElementContent(document, "MediaId");
		this.title = getElementContent(document, "Title");
		this.description = getElementContent(document, "Description");
		this.msgId = getElementContent(document, "MsgId");
	}

	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
