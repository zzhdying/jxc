 package com.mglf.finance.dto;
 
 public class PayAccountDto
 {
   public static int TYPE_PAY = 0;
 
   public static int TYPE_GATHERING = 1;
   private String payAccountCode;
   private String name;
   private int type;
 
   public String getPayAccountCode()
   {
     return this.payAccountCode;
   }
 
   public void setPayAccountCode(String payAccountCode) {
     this.payAccountCode = payAccountCode;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public int getType() {
     return this.type;
   }
 
   public void setType(int type) {
     this.type = type;
   }
 }