 package com.mglf.finance.dto.book;
 
 public class AlipayBookRecordDto extends BookRecordDto
 {
   private String document;
   private String alipayAccount;
   private String alipayAccountName;
   private boolean charge;
   private String payAccountCode;
 
   public String getAlipayAccountName()
   {
     return this.alipayAccountName;
   }
 
   public void setAlipayAccountName(String alipayAccountName) {
     this.alipayAccountName = alipayAccountName;
   }
 
   public String getDocument() {
     return this.document;
   }
 
   public void setDocument(String document) {
     this.document = document;
   }
 
   public String getAlipayAccount() {
     return this.alipayAccount;
   }
 
   public void setAlipayAccount(String alipayAccount) {
     this.alipayAccount = alipayAccount;
   }
 
   public boolean isCharge() {
     return this.charge;
   }
 
   public void setCharge(boolean charge) {
     this.charge = charge;
   }
 
   public String getPayAccountCode() {
     return this.payAccountCode;
   }
 
   public void setPayAccountCode(String payAccountCode) {
     this.payAccountCode = payAccountCode;
   }
 }