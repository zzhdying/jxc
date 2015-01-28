package com.mglf.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mglf.dto.JsonResult;
import com.mglf.dto.MsgContent;
import com.mglf.dto.MsgContent.MESSAGE_TYPE;
import com.mglf.dto.MsgContent.USER_TYPE;
import com.mglf.dto.MsgQueryCondition;
import com.mglf.dto.MsgQueryResult;
import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.JsonUtil;

/**
 * ActiveMQ即时消息服务类
 * 
 * @author zhongzhuohan
 * 
 * Example:
 * 	1）发送
 * 		发送邮件
 * 			MsgContent msg1 = new MsgContent(MsgContent.MESSAGE_TYPE.APP_EMAIL.getValue(), MsgContent.USER_TYPE.ADMIN.getValue(), "xxx@126.com", "uuid xxx", "name xxx", "subject主题 xxx", "<html>html body内容xxx</html>");		
 *			messageService.sendMessage(msg1);
 *		发送激活邮件
 *			MsgContent msg2 = new MsgContent(MsgContent.MESSAGE_TYPE.APP_ACCOUNT.getValue(), MsgContent.USER_TYPE.ADMIN.getValue(), "xxx@126.com", "uuid xxx", "name xxx", "subject激活主题", "<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\"></head>body激活内容</html>");
 *			messageService.sendMessage(msg2);
 *		发送短信
 *			MsgContent msg3 = new MsgContent(MsgContent.MESSAGE_TYPE.APP_SMS.getValue(), MsgContent.USER_TYPE.ADMIN.getValue(), "13754646754", "uuid xxx", "name xxx", null, "test");
 *			messageService.sendMessage(msg3);
 *		发送站内信
 *			MsgContent msg4 = new MsgContent(MsgContent.MESSAGE_TYPE.APP_INSTATION.getValue(), MsgContent.USER_TYPE.ADMIN.getValue(), null, "uuid xxx", "name xxx", "subject主题", "内容xxxx");
 *			messageService.sendMessage(msg4);
 *		发送站内信（全站个人用户）
 *			MsgContent msg5 = new MsgContent(MsgContent.MESSAGE_TYPE.APP_INSTATION.getValue(), MsgContent.USER_TYPE.ALL_PER.getValue(), null, null, "name xxx", "subject主题", "内容xxxx");
 *			messageService.sendMessage(msg5);
 *
 *	2）发件人设置
 *		MsgContent msg1 = new MsgContent(xx,xx,xx,xx,...);
 *		msg1.setFromName("name xxx");
 *		msg1.setFromType(MsgContent.USER_TYPE.ADMIN.getValue());
 *		msg1.setFromUuid("uuid xxx");
 *
 *	3）消息查询
 *		普通查询
 *			MsgQueryCondition mqc = new MsgQueryCondition();	//设置消息查询条件
 *			mqc.setXXXX("xxxxx");
 *			messageService.queryMsg(mqc);						//调用查询
 *		查询站内信（查询某个用户的站内信）
 *			MsgQueryCondition mqc = new MsgQueryCondition();
 *			mqc.setMsgType(MsgContent.MESSAGE_TYPE.APP_INSTATION.getValue());
 *			mqc.setToType(MsgContent.USER_TYPE.PER.getValue());
 *			mqc.setToUuid("be55ce0c-b36d-4882-9ac3-db5d26b361b6");
 *			MsgQueryResult mqr = messageService.queryMsg(mqc);
 *		查询站内信（查询某个用户的站内信，包括全站个人用户通知）
 *			MsgQueryCondition mqc = new MsgQueryCondition();
 *			mqc.setMsgType(MsgContent.MESSAGE_TYPE.APP_INSTATION.getValue());
 *			mqc.setToType(MsgContent.USER_TYPE.PER.getValue());
 *			mqc.setToUuid("be55ce0c-b36d-4882-9ac3-db5d26b361b6");
 *			mqc.setIncludeToAll(true);
 *			MsgQueryResult mqr = messageService.queryMsg(mqc);
 *		查询站内信（查询某个用户的站内信，包括全站个人用户通知，同时是未读的信息）
 *			MsgQueryCondition mqc = new MsgQueryCondition();
 *			mqc.setMsgType(MsgContent.MESSAGE_TYPE.APP_INSTATION.getValue());
 *			mqc.setToType(MsgContent.USER_TYPE.PER.getValue());
 *			mqc.setToUuid("be55ce0c-b36d-4882-9ac3-db5d26b361b6");
 *			mqc.setIncludeToAll(true);
 *			mqc.setUnread(true);
 *			MsgQueryResult mqr = messageService.queryMsg(mqc);
 *
 *	4）设置消息已读
 *		List<String> idList = new ArrayList<String>();
 *		idList.add(mqr.getList().get(0).getId());
 *		messageService.setMsgRead(idList, "be55ce0c-b36d-4882-9ac3-db5d26b361b6");
 *
 *
 *
 */
public class MessageService {

	private JmsTemplate jmsAppWei;
	private JmsTemplate jmsAppSms;
	private JmsTemplate jmsAppMail;
	private JmsTemplate jmsAppInstation;

	private JmsTemplate jmsSysSR;
	private JmsTemplate jmsSysSU;
	
	private JmsTemplate jmsSysCache;

	
	
	
	public JmsTemplate getJmsSysCache() {
		return jmsSysCache;
	}

	public void setJmsSysCache(JmsTemplate jmsSysCache) {
		this.jmsSysCache = jmsSysCache;
	}

	public JmsTemplate getJmsAppWei() {
		return jmsAppWei;
	}

	public void setJmsAppWei(JmsTemplate jmsAppWei) {
		this.jmsAppWei = jmsAppWei;
	}

	public JmsTemplate getJmsAppSms() {
		return jmsAppSms;
	}

	public void setJmsAppSms(JmsTemplate jmsAppSms) {
		this.jmsAppSms = jmsAppSms;
	}

	public JmsTemplate getJmsAppMail() {
		return jmsAppMail;
	}

	public void setJmsAppMail(JmsTemplate jmsAppMail) {
		this.jmsAppMail = jmsAppMail;
	}

	public JmsTemplate getJmsAppInstation() {
		return jmsAppInstation;
	}

	public void setJmsAppInstation(JmsTemplate jmsAppInstation) {
		this.jmsAppInstation = jmsAppInstation;
	}

	public JmsTemplate getJmsSysSR() {
		return jmsSysSR;
	}

	public void setJmsSysSR(JmsTemplate jmsSysSR) {
		this.jmsSysSR = jmsSysSR;
	}
	
	public JmsTemplate getJmsSysSU() {
		return jmsSysSU;
	}

	public void setJmsSysSU(JmsTemplate jmsSysSU) {
		this.jmsSysSU = jmsSysSU;
	}

	private void checkWeiMessage(final MsgContent msgContent) throws Exception {
		if(msgContent.getToType() != USER_TYPE.PER.getValue()){
			throw new Exception("消息目的类型错误");
		}	
		if(msgContent.getTo() == null){
			throw new Exception("消息目的不能为空");
		}

//		if(msgContent.getToName() == null){
//			throw new Exception("消息目的NAME不能为空");
//		}
			
		if(msgContent.getToUuid() == null){
			throw new Exception("消息目的UUID不能为空");
		}
		
		if(msgContent.getMessage() == null){
			throw new Exception("消息内容不能为空");
		}
	}
	private void checkMailMessage(final MsgContent msgContent) throws Exception {
		if(msgContent.getToType() != USER_TYPE.ENT.getValue() 
				&& msgContent.getToType() != USER_TYPE.PER.getValue()
				&& msgContent.getToType() != USER_TYPE.ADMIN.getValue()){
			throw new Exception("消息目的类型错误");
		}	
		if(msgContent.getTo() == null){
			throw new Exception("消息目的不能为空");
		}

//		if(msgContent.getToName() == null){
//			throw new Exception("消息目的NAME不能为空");
//		}
			
		if(msgContent.getToUuid() == null){
			throw new Exception("消息目的UUID不能为空");
		}
		if(msgContent.getSubject() == null){
			throw new Exception("消息主题不能为空");
		}
		if(msgContent.getMessage() == null){
			throw new Exception("消息内容不能为空");
		}
		
		String key = "MailMessage_"+msgContent.getTo();
		Integer count = (Integer)CacheUtil.load(CacheUtil.GROUP_MESSAGE, key);
		if(count == null){
			count = 0;
		}
		count = count.intValue() + 1;
		
		CacheUtil.save(CacheUtil.GROUP_MESSAGE, key, count);
		
		if(count.intValue() > 10){
			throw new Exception("消息发送太频繁");
		}
	}
	private void checkSmsMessage(final MsgContent msgContent) throws Exception {
		if(msgContent.getToType() != USER_TYPE.ENT.getValue() 
				&& msgContent.getToType() != USER_TYPE.PER.getValue()
				&& msgContent.getToType() != USER_TYPE.ADMIN.getValue()){
			throw new Exception("消息目的类型错误");
		}	
		if(msgContent.getTo() == null){
			throw new Exception("消息目的不能为空");
		}

//		if(msgContent.getToName() == null){
//			throw new Exception("消息目的NAME不能为空");
//		}
			
		if(msgContent.getToUuid() == null){
			throw new Exception("消息目的UUID不能为空");
		}
		
		if(msgContent.getMessage() == null){
			throw new Exception("消息内容不能为空");
		}
		
		String key = "SmsMessage_"+msgContent.getTo();
		Integer count = (Integer)CacheUtil.load(CacheUtil.GROUP_MESSAGE, key);
		if(count == null){
			count = 0;
		}
		count = count.intValue() + 1;
		
		CacheUtil.save(CacheUtil.GROUP_MESSAGE, key, count);
		
		if(count.intValue() > 2){
			throw new Exception("消息发送太频繁");
		}
	}
	private void checkInstationMessage(final MsgContent msgContent) throws Exception {
		if(msgContent.getToType() != USER_TYPE.ENT.getValue() 
				&& msgContent.getToType() != USER_TYPE.PER.getValue()
				&& msgContent.getToType() != USER_TYPE.ADMIN.getValue()
				&& msgContent.getToType() != USER_TYPE.ALL_ADMIN.getValue()
				&& msgContent.getToType() != USER_TYPE.ALL_ENT.getValue()
				&& msgContent.getToType() != USER_TYPE.ALL_PER.getValue()){
			throw new Exception("消息目的类型错误");
		}
		
		if(msgContent.getToType() != USER_TYPE.ALL_ADMIN.getValue()
				&& msgContent.getToType() != USER_TYPE.ALL_ENT.getValue()
				&& msgContent.getToType() != USER_TYPE.ALL_PER.getValue()){

//			if(msgContent.getToName() == null){
//				throw new Exception("消息目的NAME不能为空");
//			}
				
			if(msgContent.getToUuid() == null){
				throw new Exception("消息目的UUID不能为空");
			}			
		}

//		if(msgContent.getSubject() == null){
//			throw new Exception("消息主题不能为空");
//		}
		if(msgContent.getMessage() == null){
			throw new Exception("消息内容不能为空");
		}
	}
	
	
	/**
	 * 发送消息
	 * 
	 * @param MsgContent
	 *            消息
	 */
	public void sendMessage(final MsgContent msgContent) throws Exception {
		JmsTemplate jt = null;

		if (msgContent.getMsgType() == MESSAGE_TYPE.APP_WEIXIN.getValue()) {
			checkWeiMessage(msgContent);
			jt = jmsAppWei;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.APP_SMS.getValue()) {
			checkSmsMessage(msgContent);
			jt = jmsAppSms;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.APP_EMAIL.getValue() 
				|| msgContent.getMsgType() == MESSAGE_TYPE.APP_ACCOUNT.getValue()) {
			checkMailMessage(msgContent);
			jt = jmsAppMail;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.APP_INSTATION
				.getValue()) {
			checkInstationMessage(msgContent);
			jt = jmsAppInstation;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.SYS_STATIC_RESOURCE
				.getValue()) {
			jt = jmsSysSR;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.SYS_SHORT_URL
				.getValue()) {
			jt = jmsSysSU;
		} else if (msgContent.getMsgType() == MESSAGE_TYPE.SYS_CACHE
				.getValue()) {
			jt = jmsSysCache;
		}

		if (jt == null) {
			throw new Exception("消息类型错误");
		}
		

		for(int i=0; i<3; i++){
			try{
				jt.send(new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						String text = JSONObject.fromObject(msgContent).toString();
						Message msg =  session.createTextMessage(text);
						msg.setJMSRedelivered(true);
						if(msgContent.getPriority() >=0 ){
							msg.setJMSPriority(msgContent.getPriority());
						}
						return msg;
					}
				});
				
				break;
			}catch(Exception e){
				if(i>=2){
					throw e;	
				}
			}
		}

	}

	/**
	 * 设置消息已读取
	 */
	public void setMsgRead(List<String> idList, String userId) throws Exception {
		PostMethod postMethod = null;
		String resp = "";
		
		String url = ConfigUtil.readSysValue("MSG.SERVER.URL")+"/msg/setMsgRead";
		
		JSONArray jsonArray = JSONArray.fromObject(idList);		


		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		client.getHttpConnectionManager().getParams().setSoTimeout(3000);
		postMethod = new PostMethod(url);
		
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		NameValuePair[] nvps = {new NameValuePair("idList",jsonArray.toString()), new NameValuePair("userId", userId)}; 
		postMethod.setRequestBody(nvps);
		int statusCode = client.executeMethod(postMethod);
			
		if (statusCode == HttpStatus.SC_OK) {
			resp = postMethod.getResponseBodyAsString();
		}
		
		postMethod.releaseConnection();
		
		JSONObject json = JSONObject.fromObject(resp);
		if(!JsonResult.SUCCESS.equals(json.get("status"))){
			throw new Exception("setMsgRead失败！");
		}
	}
	
	
	/**
	 * 设置消息删除
	 */
	public void setMsgDel(List<String> idList, String userId) throws Exception {
		PostMethod postMethod = null;
		String resp = "";
		
		String url = ConfigUtil.readSysValue("MSG.SERVER.URL")+"/msg/setMsgDel";
		
		JSONArray jsonArray = JSONArray.fromObject(idList);		


		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		client.getHttpConnectionManager().getParams().setSoTimeout(3000);
		postMethod = new PostMethod(url);
		
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		NameValuePair[] nvps = {new NameValuePair("idList",jsonArray.toString()), new NameValuePair("userId", userId)}; 
		postMethod.setRequestBody(nvps);
		int statusCode = client.executeMethod(postMethod);
			
		if (statusCode == HttpStatus.SC_OK) {
			resp = postMethod.getResponseBodyAsString();
		}
		
		postMethod.releaseConnection();
		
		JSONObject json = JSONObject.fromObject(resp);
		if(!JsonResult.SUCCESS.equals(json.get("status"))){
			throw new Exception("setMsgDel失败！");
		}
	}
	
	/**
	 * 查询消息
	 * @return 
	 */
	public MsgQueryResult queryMsg(MsgQueryCondition mqc) throws Exception {
		MsgQueryResult result = new MsgQueryResult();
		PostMethod postMethod = null;
		String resp = "";
		
		String url = ConfigUtil.readSysValue("MSG.SERVER.URL")+"/msg/queryMsg";
		
		JSONObject json = JSONObject.fromObject(mqc);		


		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		client.getHttpConnectionManager().getParams().setSoTimeout(5000);
		postMethod = new PostMethod(url);
		
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		
		NameValuePair[] nvps = {new NameValuePair("condition",json.toString())}; 
		postMethod.setRequestBody(nvps);
		int statusCode = client.executeMethod(postMethod);
			
		if (statusCode == HttpStatus.SC_OK) {
			resp = postMethod.getResponseBodyAsString();
		}
		
		postMethod.releaseConnection();
		
		json = JSONObject.fromObject(resp);
		JSONArray array = json.getJSONArray("list");
		
		List<MsgContent> list = new ArrayList<MsgContent>(array.size());
		
		for(int i=0; i<array.size(); i++){
			JSONObject item = array.getJSONObject(i);
			MsgContent c = (MsgContent)JsonUtil.jsonToBean(item, MsgContent.class);
			
			list.add(c);
		}
		result.setList(list);
		
		result.setCount(json.getLong("count"));
		
		return result;
	}
	
	
	
	
}
