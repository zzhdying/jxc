package com.mglf.entity;

import com.mglf.base.BaseEntity;

public class Stock  extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String entid;

    private String prdTypeId;

    private Integer all;

    private Integer leave;

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

    public String getPrdTypeId() {
        return prdTypeId;
    }

    public void setPrdTypeId(String prdTypeId) {
        this.prdTypeId = prdTypeId == null ? null : prdTypeId.trim();
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }
}