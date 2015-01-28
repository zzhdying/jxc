 package com.mglf.finance.dto.pay;
 
 import java.math.BigDecimal;
 
 public class PayResultDto
 {
   public static int PAY_STATUS_SUCCESS = 0;
   public static int PAY_STATUS_FAIL = 1;
   public static int PAY_STATUS_FIX = 2;
   private String id;
   private String payAccountCode;
   private BigDecimal amount;
   private String optBatchId;
   private int payStatus;
 
   public String getId()
   {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public String getPayAccountCode() {
     return this.payAccountCode;
   }
 
   public void setPayAccountCode(String payAccountCode) {
     this.payAccountCode = payAccountCode;
   }
 
   public BigDecimal getAmount() {
     return this.amount;
   }
 
   public void setAmount(BigDecimal amount) {
     this.amount = amount;
   }
 
   public String getOptBatchId() {
     return this.optBatchId;
   }
 
   public void setOptBatchId(String optBatchId) {
     this.optBatchId = optBatchId;
   }
 
   public int getPayStatus() {
     return this.payStatus;
   }
 
   public void setPayStatus(int payStatus) {
     this.payStatus = payStatus;
   }
 }