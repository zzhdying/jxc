 package com.mglf.finance.dto.withdrawals;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public class WithdrawalsRequestDto
 {
   public static final int OPT_STATUS_OPEN = 0;
   public static final int OPT_STATUS_PROCESS = 1;
   public static final int OPT_STATUS_PROCESS_ERR = 2;
   public static final int OPT_STATUS_SUCCESS = 3;
   public static final int OPT_STATUS_FAIL = 4;
   private String id;
   private BigDecimal amount;
   private Date applyDatetime;
   private String optBatchId;
   private Date optDatetime;
   private int optStatus;
   private String optMsg;
   private String payAccountCode;
   private String payAccountInfo;
 
   public String getPayAccountCode()
   {
     return this.payAccountCode;
   }
 
   public void setPayAccountCode(String payAccountCode) {
     this.payAccountCode = payAccountCode;
   }
 
   public String getPayAccountInfo() {
     return this.payAccountInfo;
   }
 
   public void setPayAccountInfo(String payAccountInfo) {
     this.payAccountInfo = payAccountInfo;
   }
 
   public String getOptMsg() {
     return this.optMsg;
   }
 
   public void setOptMsg(String optMsg) {
     this.optMsg = optMsg;
   }
 
   public String getId() {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public BigDecimal getAmount() {
     return this.amount;
   }
 
   public void setAmount(BigDecimal amount) {
     this.amount = amount;
   }
 
   public Date getApplyDatetime() {
     return this.applyDatetime;
   }
 
   public void setApplyDatetime(Date applyDatetime) {
     this.applyDatetime = applyDatetime;
   }
 
   public String getOptBatchId() {
     return this.optBatchId;
   }
 
   public void setOptBatchId(String optBatchId) {
     this.optBatchId = optBatchId;
   }
 
   public Date getOptDatetime() {
     return this.optDatetime;
   }
 
   public void setOptDatetime(Date optDatetime) {
     this.optDatetime = optDatetime;
   }
 
   public int getOptStatus() {
     return this.optStatus;
   }
 
   public void setOptStatus(int optStatus) {
     this.optStatus = optStatus;
   }
 }