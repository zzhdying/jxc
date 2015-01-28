package com.fescoadecco.weixin.session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.fescoadecco.weixin.dto.msg.Msg;
import com.fescoadecco.weixin.dto.msg.Msg4Event;
import com.fescoadecco.weixin.dto.msg.Msg4Head;
import com.fescoadecco.weixin.dto.msg.Msg4Image;
import com.fescoadecco.weixin.dto.msg.Msg4Link;
import com.fescoadecco.weixin.dto.msg.Msg4Location;
import com.fescoadecco.weixin.dto.msg.Msg4Text;
import com.fescoadecco.weixin.dto.msg.Msg4Video;
import com.fescoadecco.weixin.dto.msg.Msg4Voice;

public abstract class Session {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");
	private InputStream is;
	private OutputStream os;
	private static DocumentBuilder builder;
	private static TransformerFactory tffactory;

	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		tffactory = TransformerFactory.newInstance();
	}

	public void process(InputStream is, OutputStream os) throws Exception {
		this.os = os;

		Document document = builder.parse(is);
		// 释放资源
		Msg4Head head = new Msg4Head();
		head.read(document);
		String type = head.getMsgType();

		if ("text".equals(type)) {
			Msg4Text msg = new Msg4Text(head);
			msg.read(document);
			onTextMsg(msg);
		} else if ("image".equals(type)) {
			Msg4Image msg = new Msg4Image(head);
			msg.read(document);
			onImageMsg(msg);
		} else if ("event".equals(type)) {
			Msg4Event msg = new Msg4Event(head);
			msg.read(document);
			onEventMsg(msg);
		} else if ("link".equals(type)) {
			Msg4Link msg = new Msg4Link(head);
			msg.read(document);
			onLinkMsg(msg);
		} else if ("location".equals(type)) {
			Msg4Location msg = new Msg4Location(head);
			msg.read(document);
			onLocationMsg(msg);
		} else if ("voice".equals(type)) {
			Msg4Voice msg = new Msg4Voice(head);
			msg.read(document);
			onVoiceMsg(msg);
		} else if ("video".equals(type)) {
			Msg4Video msg = new Msg4Video(head);
			msg.read(document);
			onVideoMsg(msg);
		} else {
			onErrorMsg(-1);
		}

	}

	public void callback(Msg msg) throws Exception {
		Document document = builder.newDocument();
		msg.write(document);

		Transformer transformer = tffactory.newTransformer();

		transformer.transform(new DOMSource(document), new StreamResult(
				new OutputStreamWriter(this.os, "utf-8")));

	}

	public void close() throws Exception {

		if (this.is != null) {
			this.is.close();
		}
		if (this.os != null) {
			this.os.flush();
			this.os.close();
		}

	}

	public abstract void onTextMsg(Msg4Text paramMsg4Text)  throws Exception;

	public abstract void onImageMsg(Msg4Image paramMsg4Image)  throws Exception;

	public abstract void onEventMsg(Msg4Event paramMsg4Event)  throws Exception;

	public abstract void onLinkMsg(Msg4Link paramMsg4Link)  throws Exception;

	public abstract void onLocationMsg(Msg4Location paramMsg4Location)  throws Exception;

	public abstract void onVoiceMsg(Msg4Voice paramMsg4Voice)  throws Exception;

	public abstract void onVideoMsg(Msg4Video paramMsg4Video)  throws Exception;

	public abstract void onErrorMsg(int paramInt)  throws Exception;
}
