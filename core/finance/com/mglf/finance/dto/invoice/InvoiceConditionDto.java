 package com.mglf.finance.dto.invoice;
 
 import com.mglf.dao.interceptor.SearchPageableCondition;
 import java.util.Date;
 
 public class InvoiceConditionDto extends SearchPageableCondition
 {
   private int invoiceType;
   private Date invoiceBeginDatetime;
   private Date invoiceEndDatetime;
   private String invoiceNumber;
   private String description;
   private String refData;
   private String invoiceRequestId;
 
   public int getInvoiceType()
   {
     return this.invoiceType;
   }
 
   public void setInvoiceType(int invoiceType) {
     this.invoiceType = invoiceType;
   }
 
   public Date getInvoiceBeginDatetime() {
     return this.invoiceBeginDatetime;
   }
 
   public void setInvoiceBeginDatetime(Date invoiceBeginDatetime) {
     this.invoiceBeginDatetime = invoiceBeginDatetime;
   }
 
   public Date getInvoiceEndDatetime() {
     return this.invoiceEndDatetime;
   }
 
   public void setInvoiceEndDatetime(Date invoiceEndDatetime) {
     this.invoiceEndDatetime = invoiceEndDatetime;
   }
 
   public String getInvoiceNumber() {
     return this.invoiceNumber;
   }
 
   public void setInvoiceNumber(String invoiceNumber) {
     this.invoiceNumber = invoiceNumber;
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