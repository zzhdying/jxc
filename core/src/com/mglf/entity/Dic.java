package com.mglf.entity;

import java.util.Date;

import com.mglf.base.BaseEntity;

public class Dic  extends BaseEntity {
    private String id;

    private String entid;

    private String num;
    
    private String code;

    private String name;

    private String value;

    private String desciprtion;

    private String parentId;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
    
    /**
     * 商品单位
     */
    public static final String PRD_UNIT = "PRD_UNIT";
    
    /**
     * 商品所属企业
     */
    public static final String PRD_OWNENTNAME = "PRD_OWNENTNAME";
    
    /**
     * 商品所属类别
     */
    public static final String PRD_TYPE = "PRD_TYPE";
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid == null ? null : entid.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getDesciprtion() {
        return desciprtion;
    }

    public void setDesciprtion(String desciprtion) {
        this.desciprtion = desciprtion == null ? null : desciprtion.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}