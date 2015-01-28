package com.mglf.dto;

import java.util.Date;

public class AttachmentDto {
	//ID
	private String id;
	
	//GROUP_ID
	private String groupId;
	
	//文件名
	private String fileName;
	
	//文件保存ID
	private String fileId;
	
	//文件尺寸
	private Long fileSize;
	
	//上传时间
	private Date uploadDatetime;
	
	//上传人ID
	private String uploadUserId;
	
	//数据
	private byte[] data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadDatetime() {
		return uploadDatetime;
	}

	public void setUploadDatetime(Date uploadDatetime) {
		this.uploadDatetime = uploadDatetime;
	}

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
}
