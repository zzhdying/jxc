 package com.mglf.finance.dto.withdrawals;
 
 public class EntWithdrawalsConditionDto extends WithdrawalsConditionDto
 {
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