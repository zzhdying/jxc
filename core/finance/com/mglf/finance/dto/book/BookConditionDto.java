 package com.mglf.finance.dto.book;
 
 import com.mglf.dao.interceptor.SearchPageableCondition;
 import java.util.Date;
 
 public class BookConditionDto extends SearchPageableCondition
 {
   private int accountStatus;
   private Date accountBeginDate;
   private Date accountEndDate;
   private Date closeBeginDate;
   private Date closeEndDate;
   private Date beginDate;
   private Date endDate;
   private String refData;
   private String optBatchId;
 
   public String getOptBatchId()
   {
     return this.optBatchId;
   }
 
   public void setOptBatchId(String optBatchId) {
     this.optBatchId = optBatchId;
   }
 
   public String getRefData() {
     return this.refData;
   }
 
   public void setRefData(String refData) {
     this.refData = refData;
   }
 
   public int getAccountStatus() {
     return this.accountStatus;
   }
 
   public void setAccountStatus(int accountStatus) {
     this.accountStatus = accountStatus;
   }
 
   public Date getAccountBeginDate() {
     return this.accountBeginDate;
   }
 
   public void setAccountBeginDate(Date accountBeginDate) {
     this.accountBeginDate = accountBeginDate;
   }
 
   public Date getAccountEndDate() {
     return this.accountEndDate;
   }
 
   public void setAccountEndDate(Date accountEndDate) {
     this.accountEndDate = accountEndDate;
   }
 
   public Date getCloseBeginDate() {
     return this.closeBeginDate;
   }
 
   public void setCloseBeginDate(Date closeBeginDate) {
     this.closeBeginDate = closeBeginDate;
   }
 
   public Date getCloseEndDate() {
     return this.closeEndDate;
   }
 
   public void setCloseEndDate(Date closeEndDate) {
     this.closeEndDate = closeEndDate;
   }
 
   public Date getBeginDate() {
     return this.beginDate;
   }
 
   public void setBeginDate(Date beginDate) {
     this.beginDate = beginDate;
   }
 
   public Date getEndDate() {
     return this.endDate;
   }
 
   public void setEndDate(Date endDate) {
     this.endDate = endDate;
   }
 }