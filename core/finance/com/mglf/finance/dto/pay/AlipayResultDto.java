 package com.mglf.finance.dto.pay;
 
 import java.util.Date;
 
 public class AlipayResultDto extends PayResultDto
 {
   private String alipayAccount;
   private String alipayAccountName;
   private String alipayReason;
   private Date alipayDatetime;
   private String alipayNid;
   private String alipayId;
 
   public String getAlipayAccount()
   {
     return this.alipayAccount;
   }
 
   public void setAlipayAccount(String alipayAccount) {
     this.alipayAccount = alipayAccount;
   }
 
   public String getAlipayAccountName() {
     return this.alipayAccountName;
   }
 
   public void setAlipayAccountName(String alipayAccountName) {
     this.alipayAccountName = alipayAccountName;
   }
 
   public String getAlipayReason() {
     return this.alipayReason;
   }
 
   public void setAlipayReason(String alipayReason) {
     this.alipayReason = alipayReason;
   }
 
   public Date getAlipayDatetime() {
     return this.alipayDatetime;
   }
 
   public void setAlipayDatetime(Date alipayDatetime) {
     this.alipayDatetime = alipayDatetime;
   }
 
   public String getAlipayNid() {
     return this.alipayNid;
   }
 
   public void setAlipayNid(String alipayNid) {
     this.alipayNid = alipayNid;
   }
 
   public String getAlipayId() {
     return this.alipayId;
   }
 
   public void setAlipayId(String alipayId) {
     this.alipayId = alipayId;
   }
 }