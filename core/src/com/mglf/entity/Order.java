package com.mglf.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.mglf.base.BaseEntity;

public class Order  extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String num;
    
    private String entid;

    private String supplierId;

    private BigDecimal buyPrice;
    
    private BigDecimal sellPrice;
    
    private BigDecimal prdCount;
    
    private Integer isOk;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public static int NOT_OK = 0;
    
    public static int OK = 1;
    
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
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

	public String getEntid() {
		return entid;
	}

	public void setEntid(String entid) {
		this.entid = entid;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getPrdCount() {
		return prdCount;
	}

	public void setPrdCount(BigDecimal prdCount) {
		this.prdCount = prdCount;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}
    
}