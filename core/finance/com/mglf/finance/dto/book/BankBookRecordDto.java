 package com.mglf.finance.dto.book;
 
 public class BankBookRecordDto extends BookRecordDto
 {
   public static String PAY_ACCOUNT_CODE_ICBC_OFFLINE = "ICBC_OFFLINE";
   public static String PAY_ACCOUNT_CODE_CMB_OFFLINE = "CMB_OFFLINE";
   public static String PAY_ACCOUNT_CODE_CMB_GATHERING = "CMB_GATHERING";
   public static String PAY_ACCOUNT_CODE_CMB_PAY = "CMB_PAY";
   private String document;
   private boolean charge;
   private String payAccountCode;
   private String bank;
   private String accountNo;
   private String accountName;
 
   public String getDocument()
   {
     return this.document;
   }
 
   public void setDocument(String document) {
     this.document = document;
   }
 
   public String getBank()
   {
     return this.bank;
   }
 
   public void setBank(String bank) {
     this.bank = bank;
   }
 
   public String getAccountNo() {
     return this.accountNo;
   }
 
   public void setAccountNo(String accountNo) {
     this.accountNo = accountNo;
   }
 
   public String getAccountName() {
     return this.accountName;
   }
 
   public void setAccountName(String accountName) {
     this.accountName = accountName;
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