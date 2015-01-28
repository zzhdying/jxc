 package com.mglf.finance.dto.book;
 
 public class PerBookConditionDto extends BookConditionDto
 {
   private String perUserId;
 
   public String getPerUserId()
   {
     return this.perUserId;
   }
 
   public void setPerUserId(String perUserId) {
     this.perUserId = perUserId;
   }
 }