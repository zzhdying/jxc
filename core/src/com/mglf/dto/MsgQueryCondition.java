package com.mglf.dto;

import java.io.Serializable;
import java.util.Date;

public class MsgQueryCondition implements Serializable {

	private static final long serialVersionUID = -1;

	private String id;
	
	private int msgType; // 类型
	
	private String from; //
	private String to; // 发送账户
	private int fromType = -1;
	private int toType = -1;
	private String fromUuid;
	private String toUuid;
	private String fromName;
	private String toName;
	
	private String subject; // 标题
	private String message; // 发送内容
	
	private Date sendTimeFrom;
	private Date sendTimeTo;
	
	private boolean unread = false;		//是否未读
	private boolean undel = true;		//是否删除
	
	private int status;
	
	protected int page = 1;		//页码
	protected int rows = 10;	//每页显示数量
	protected boolean autoCount = true;
	
	protected boolean includeToAll = false;	//当设置toType后，是否包含ALL_XXX类型
	
	protected Long seqFrom;
	
	protected Long seqTo;
	
	protected String refData;
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}


	

	public String getRefData() {
		return refData;
	}



	public void setRefData(String refData) {
		this.refData = refData;
	}



	public MsgQueryCondition(){
	}

	
	
	public Long getSeqFrom() {
		return seqFrom;
	}



	public void setSeqFrom(Long seqFrom) {
		this.seqFrom = seqFrom;
	}



	public Long getSeqTo() {
		return seqTo;
	}



	public void setSeqTo(Long seqTo) {
		this.seqTo = seqTo;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTimeFrom() {
		return sendTimeFrom;
	}

	public void setSendTimeFrom(Date sendTimeFrom) {
		this.sendTimeFrom = sendTimeFrom;
	}

	public Date getSendTimeTo() {
		return sendTimeTo;
	}

	public void setSendTimeTo(Date sendTimeTo) {
		this.sendTimeTo = sendTimeTo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public boolean isIncludeToAll() {
		return includeToAll;
	}

	public void setIncludeToAll(boolean includeToAll) {
		this.includeToAll = includeToAll;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public boolean isUndel() {
		return undel;
	}

	public void setUndel(boolean undel) {
		this.undel = undel;
	}



	
	
}
