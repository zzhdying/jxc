 package com.mglf.finance.dto.withdrawals;
 
 public class PerWithdrawalsConditionDto extends WithdrawalsConditionDto
 {
   private String perUserId;
   private String perName;
 
   public String getPerUserId()
   {
     return this.perUserId;
   }
 
   public void setPerUserId(String perUserId) {
     this.perUserId = perUserId;
   }
 
   public String getPerName() {
     return this.perName;
   }
 
   public void setPerName(String perName) {
     this.perName = perName;
   }
 }