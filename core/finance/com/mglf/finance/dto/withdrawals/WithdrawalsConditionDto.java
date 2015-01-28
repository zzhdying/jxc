 package com.mglf.finance.dto.withdrawals;
 
 import com.mglf.dao.interceptor.SearchPageableCondition;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.List;
 
 public class WithdrawalsConditionDto extends SearchPageableCondition
 {
   private String id;
   private BigDecimal amount;
   private Date applyBeginDatetime;
   private Date applyEndDatetime;
   private String optBatchId;
   private Date optDatetime;
   private Integer optStatus;
   private String optMsg;
   private List<String> ids;
   private List<Integer> optStatusList;
 
   public List<Integer> getOptStatusList()
   {
     return this.optStatusList;
   }
 
   public void setOptStatusList(List<Integer> optStatusList) {
     this.optStatusList = optStatusList;
   }
 
   public String getOptMsg() {
     return this.optMsg;
   }
 
   public void setOptMsg(String optMsg) {
     this.optMsg = optMsg;
   }
 
   public List<String> getIds() {
     return this.ids;
   }
 
   public void setIds(List<String> ids) {
     this.ids = ids;
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
 
   public Date getApplyBeginDatetime()
   {
     return this.applyBeginDatetime;
   }
 
   public void setApplyBeginDatetime(Date applyBeginDatetime) {
     this.applyBeginDatetime = applyBeginDatetime;
   }
 
   public Date getApplyEndDatetime() {
     return this.applyEndDatetime;
   }
 
   public void setApplyEndDatetime(Date applyEndDatetime) {
     this.applyEndDatetime = applyEndDatetime;
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
 
   public Integer getOptStatus() {
     return this.optStatus;
   }
 
   public void setOptStatus(Integer optStatus) {
     this.optStatus = optStatus;
   }
 }