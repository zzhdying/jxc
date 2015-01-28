package com.mglf.dto;

import java.io.Serializable;
import java.util.Date;

public class MsgContent implements Serializable {

	private static final long serialVersionUID = -1;

	private int msgType; // 类型
	
	private String from; //
	private String to; // 发送账户
	private int fromType;
	private int toType;
	private String fromUuid;
	private String toUuid;
	private String fromName;
	private String toName;
	
	private String subject; // 标题
	private String message; // 发送内容
	
	private int priority; // 优先级

	
	private String id;
	private Date sendTime;
	
	private int status;
	
	private Date readDatetime; //读取时间
	
	private long seq;
	
	private String refData;
	
	public final static int STATUS_SUCCESS = 1;
	public final static int STATUS_SENT = 2;
	public final static int STATUS_FAIL = 3;
		
	public MsgContent(int msgType, int toType,String to, String toUuid, String toName, String subject, String message){
		this.msgType = msgType;
		this.toType = toType;
		this.toUuid = toUuid;
		this.toName = toName;
		this.to = to;
		this.subject = subject;
		this.message = message;
		
		this.fromType = USER_TYPE.SYS.value;
	}
	
	public MsgContent(int msgType, int toType, String to, String toUuid, String toName, String message){
		this.msgType = msgType;
		this.toType = toType;
		this.toUuid = toUuid;
		this.toName = toName;
		this.to = to;
		this.message = message;
		
		this.fromType = USER_TYPE.SYS.value;
	}
	
	public MsgContent(int msgType, String message){
		this.msgType = msgType;
		this.message = message;
		
		this.fromType = USER_TYPE.SYS.value;
		this.toType = USER_TYPE.SYS.value;
	}
	
	public enum USER_TYPE {
		
		SYS("系统", 0),
		
		ADMIN("后台用户", 100),
		
		ENT("企业用户", 200),
		
		PER("个人用户", 300),
		
		ALL_ADMIN("所有后台用户", 101),
		
		ALL_ENT("所有企业用户", 201),
		
		ALL_PER("所有个人用户", 301);
		
		
		private String name;

		private Integer value;
		
		private USER_TYPE(String displayName, Integer value) {
			this.name = displayName;
			this.value = value;
		}
		
		public static String getDisPlayName(Integer index) {

			for (MESSAGE_TYPE item : MESSAGE_TYPE.values()) {
				if (item.value.equals(index)) {
					return item.name;
				}
			}
			return "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
	
	public enum MESSAGE_TYPE {
		SYS_STATIC_RESOURCE("静态资源", 100),
		SYS_SHORT_URL("短地址保存", 101),
		SYS_CACHE("系统缓存", 102),
		
		APP_SMS("短信", 201),

		APP_EMAIL("邮件", 202),

		APP_WEIXIN("微信", 203),

		APP_ACCOUNT("账户激活", 204),

		APP_INSTATION("站内信", 205);
		
		private String name;

		private Integer value;

		private MESSAGE_TYPE(String displayName, Integer value) {
			this.name = displayName;
			this.value = value;
		}

		public static String getDisPlayName(Integer index) {

			for (MESSAGE_TYPE item : MESSAGE_TYPE.values()) {
				if (item.value.equals(index)) {
					return item.name;
				}
			}
			return "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

	
	
	public String getRefData() {
		return refData;
	}

	public void setRefData(String refData) {
		this.refData = refData;
	}

	public MsgContent(){
		this.fromType = USER_TYPE.SYS.value;
	}
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}



	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public int getToType() {
		return toType;
	}

	public void setToType(int toType) {
		this.toType = toType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getFromUuid() {
		return fromUuid;
	}

	public void setFromUuid(String fromUuid) {
		this.fromUuid = fromUuid;
	}

	public String getToUuid() {
		return toUuid;
	}

	public void setToUuid(String toUuid) {
		this.toUuid = toUuid;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isRead() {
		return readDatetime != null;
	}

	public Date getReadDatetime() {
		return readDatetime;
	}

	public void setReadDatetime(Date readDatetime) {
		this.readDatetime = readDatetime;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	
	
	
	

}
