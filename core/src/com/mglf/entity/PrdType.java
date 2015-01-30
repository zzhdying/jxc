package com.mglf.entity;

import java.util.Date;

import com.mglf.base.BaseEntity;

public class PrdType  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String num;

    private String entid;
    
    private String name;

    private String pararentid;

    private String code;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getPararentid() {
        return pararentid;
    }

    public void setPararentid(String pararentid) {
        this.pararentid = pararentid == null ? null : pararentid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc == null ? null : desc.trim();
    }

	public String getEntid() {
		return entid;
	}

	public void setEntid(String entid) {
		this.entid = entid;
	}
    
    
}