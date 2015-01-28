 package com.mglf.finance.dto.book;
 
 public class AlipayBookConditionDto extends BookConditionDto
 {
   private String payAccountCode;
 
   public String getPayAccountCode()
   {
     return this.payAccountCode;
   }
 
   public void setPayAccountCode(String payAccountCode) {
     this.payAccountCode = payAccountCode;
   }
 }