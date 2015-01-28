 package com.mglf.finance.dto.invoice;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public class InvoiceRecordDto
 {
   private String id;
   private int invoiceType;
   private BigDecimal invoiceAmount;
   private Date invoiceDatetime;
   private String invoiceNumber;
   private int optUserType;
   private String optUserId;
   private String optUserName;
   private Date optDatetime;
   private String description;
   private String refData;
   private String invoiceRequestId;
 
   public String getId()
   {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public int getInvoiceType() {
     return this.invoiceType;
   }
 
   public void setInvoiceType(int invoiceType) {
     this.invoiceType = invoiceType;
   }
 
   public BigDecimal getInvoiceAmount() {
     return this.invoiceAmount;
   }
 
   public void setInvoiceAmount(BigDecimal invoiceAmount) {
     this.invoiceAmount = invoiceAmount;
   }
 
   public Date getInvoiceDatetime() {
     return this.invoiceDatetime;
   }
 
   public void setInvoiceDatetime(Date invoiceDatetime) {
     this.invoiceDatetime = invoiceDatetime;
   }
 
   public String getInvoiceNumber() {
     return this.invoiceNumber;
   }
 
   public void setInvoiceNumber(String invoiceNumber) {
     this.invoiceNumber = invoiceNumber;
   }
 
   public int getOptUserType() {
     return this.optUserType;
   }
 
   public void setOptUserType(int optUserType) {
     this.optUserType = optUserType;
   }
 
   public String getOptUserId() {
     return this.optUserId;
   }
 
   public void setOptUserId(String optUserId) {
     this.optUserId = optUserId;
   }
 
   public String getOptUserName() {
     return this.optUserName;
   }
 
   public void setOptUserName(String optUserName) {
     this.optUserName = optUserName;
   }
 
   public Date getOptDatetime() {
     return this.optDatetime;
   }
 
   public void setOptDatetime(Date optDatetime) {
     this.optDatetime = optDatetime;
   }
 
   public String getDescription() {
     return this.description;
   }
 
   public void setDescription(String description) {
     this.description = description;
   }
 
   public String getRefData() {
     return this.refData;
   }
 
   public void setRefData(String refData) {
     this.refData = refData;
   }
 
   public String getInvoiceRequestId() {
     return this.invoiceRequestId;
   }
 
   public void setInvoiceRequestId(String invoiceRequestId) {
     this.invoiceRequestId = invoiceRequestId;
   }
 }