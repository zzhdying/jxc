 package com.mglf.finance.dto.invoice;
 
 public class EntInvoiceRecordDto extends InvoiceRecordDto
 {
   public static final int INVOICE_TYPE_OFFER = 0;
   private String entId;
   private String entName;
   private String entUserId;
   private String entUserName;
 
   public String getEntId()
   {
     return this.entId;
   }
 
   public void setEntId(String entId) {
     this.entId = entId;
   }
 
   public String getEntName() {
     return this.entName;
   }
 
   public void setEntName(String entName) {
     this.entName = entName;
   }
 
   public String getEntUserId() {
     return this.entUserId;
   }
 
   public void setEntUserId(String entUserId) {
     this.entUserId = entUserId;
   }
 
   public String getEntUserName() {
     return this.entUserName;
   }
 
   public void setEntUserName(String entUserName) {
     this.entUserName = entUserName;
   }
 }