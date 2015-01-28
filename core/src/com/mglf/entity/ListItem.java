package com.mglf.entity;

import java.util.Date;

import com.mglf.base.BaseEntity;

public class ListItem  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String num;

    private String listid;

    private String prdTypeId;

    private String entid;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

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

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid == null ? null : listid.trim();
    }

    public String getPrdTypeId() {
        return prdTypeId;
    }

    public void setPrdTypeId(String prdTypeId) {
        this.prdTypeId = prdTypeId == null ? null : prdTypeId.trim();
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid == null ? null : entid.trim();
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
}