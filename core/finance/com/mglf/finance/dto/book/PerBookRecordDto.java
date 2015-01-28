 package com.mglf.finance.dto.book;
 
 public class PerBookRecordDto extends BookRecordDto
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