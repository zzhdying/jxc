 package com.mglf.finance.dto.book;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public abstract class BookRecordDto
 {
   private String id;
   private BigDecimal amount;
   private String batchId;
   private Date accountingDatetime;
   private int optStatus;
   private Date closeDatetime;
   private String optUserName;
   private String optUserId;
   private int optUserType;
   private int optType;
   private String description;
   private String refData;
   private BookRecordDto targetBookRecord;
 
   public BookRecordDto getTargetBookRecord()
   {
     return this.targetBookRecord;
   }
 
   public void setTargetBookRecord(BookRecordDto targetBookRecord) {
     this.targetBookRecord = targetBookRecord;
   }
 
   public String getDescription() {
     return this.description;
   }
 
   public void setDescription(String description) {
     this.description = description;
   }
 
   public String getRefData()
   {
     return this.refData;
   }
 
   public void setRefData(String refData) {
     this.refData = refData;
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
 
   public String getBatchId() {
     return this.batchId;
   }
 
   public void setBatchId(String batchId) {
     this.batchId = batchId;
   }
 
   public Date getAccountingDatetime() {
     return this.accountingDatetime;
   }
 
   public void setAccountingDatetime(Date accountingDatetime) {
     this.accountingDatetime = accountingDatetime;
   }
 
   public int getOptStatus() {
     return this.optStatus;
   }
 
   public void setOptStatus(int optStatus) {
     this.optStatus = optStatus;
   }
 
   public Date getCloseDatetime() {
     return this.closeDatetime;
   }
 
   public void setCloseDatetime(Date closeDatetime) {
     this.closeDatetime = closeDatetime;
   }
 
   public String getOptUserName() {
     return this.optUserName;
   }
 
   public void setOptUserName(String optUserName) {
     this.optUserName = optUserName;
   }
 
   public String getOptUserId() {
     return this.optUserId;
   }
 
   public void setOptUserId(String optUserId) {
     this.optUserId = optUserId;
   }
 
   public int getOptUserType() {
     return this.optUserType;
   }
 
   public void setOptUserType(int optUserType) {
     this.optUserType = optUserType;
   }
 
   public int getOptType() {
     return this.optType;
   }
 
   public void setOptType(int optType) {
     this.optType = optType;
   }
 }