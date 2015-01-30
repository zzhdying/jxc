package com.mglf.entity;

import java.util.Date;

import com.mglf.base.BaseEntity;

public class OrderList  extends BaseEntity {
    private String id;

    private String prdTypeId;

    private Integer count;

    private Long unitPrice;

    private Long actPrice;

    private Long allPrice;

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

    public String getPrdTypeId() {
        return prdTypeId;
    }

    public void setPrdTypeId(String prdTypeId) {
        this.prdTypeId = prdTypeId == null ? null : prdTypeId.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getActPrice() {
        return actPrice;
    }

    public void setActPrice(Long actPrice) {
        this.actPrice = actPrice;
    }

    public Long getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Long allPrice) {
        this.allPrice = allPrice;
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