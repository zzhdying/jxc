package com.mglf.finance.dto;

import java.util.Date;

public class OptRecordDto
{
  public static final int OPT_STATUS_OPEN = 0;
  public static final int OPT_STATUS_CLOSE = 1;
  public static final int OPT_STATUS_CANCEL = 2;
  public static final int OPT_TYPE_ALIPAY2ENT = 800300;
  public static final int OPT_TYPE_BANK2ENT = 810300;
  public static final int OPT_TYPE_ENT2ALIPAY = 300800;
  public static final int OPT_TYPE_ENT2BANK = 300810;
  public static final int OPT_TYPE_ENT2SYS = 300100;
  public static final int OPT_TYPE_SYS2ENT = 100300;
  public static final int OPT_TYPE_ENTREC2SYS = 310100;
  public static final int OPT_TYPE_SYS2ENTREC = 100310;
  public static final int OPT_TYPE_BANK2ENTREC = 810310;
  public static final int OPT_TYPE_PER2ALIPAY = 600800;
  public static final int OPT_TYPE_ALIPAY2PER = 800600;
  public static final int OPT_TYPE_PER2BANK = 600810;
  public static final int OPT_TYPE_SYS2PER = 100600;
  public static final int OPT_TYPE_SYS2PERPAY = 100610;
  public static final int OPT_TYPE_SYS2BANK = 100810;
  public static final int OPT_TYPE_INVOICE_ENT = 900001;
  public static final int OPT_USER_TYPE_SYS = 0;
  public static final int OPT_USER_TYPE_ADMIN = 100;
  public static final int OPT_USER_TYPE_ENT = 200;
  public static final int OPT_USER_TYPE_PER = 300;
  private String id;
  private int optType;
  private int optUserType;
  private String optUserId;
  private String optUserName;
  private Date optDatetime;
  private int optStatus;
  private String description;
  private Date closeDatetime;
  private String refData;

  public String getRefData()
  {
     return this.refData;
  }

  public void setRefData(String refData) {
     this.refData = refData;
  }

  public String getId() {
     return this.id;
  }

  public void setId(String id) {
     this.id = id;
  }

  public int getOptType() {
     return this.optType;
  }

  public void setOptType(int optType) {
     this.optType = optType;
  }

  public int getOptUserType() {
     return this.optUserType;
  }

  public void setOptUserType(int optUserType) {
     this.optUserType = optUserType;
  }

  public String getOptUserId() {
     return this.optUserId;
  }

  public void setOptUserId(String optUserId) {
     this.optUserId = optUserId;
  }

  public String getOptUserName() {
     return this.optUserName;
  }

  public void setOptUserName(String optUserName) {
     this.optUserName = optUserName;
  }

  public Date getOptDatetime() {
     return this.optDatetime;
  }

  public void setOptDatetime(Date optDatetime) {
     this.optDatetime = optDatetime;
  }

  public int getOptStatus() {
     return this.optStatus;
  }

  public void setOptStatus(int optStatus) {
     this.optStatus = optStatus;
  }

  public String getDescription() {
     return this.description;
  }

  public void setDescription(String description) {
     this.description = description;
  }

  public Date getCloseDatetime() {
     return this.closeDatetime;
  }

  public void setCloseDatetime(Date closeDatetime) {
     this.closeDatetime = closeDatetime;
  }
}