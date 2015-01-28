 package com.mglf.finance.dto.book;
 
 import java.math.BigDecimal;
 import java.util.Date;
 
 public class BookDto
 {
   private String id;
   private String bookCode;
   private String bookName;
   private Date summaryDate;
   private BigDecimal debitAmount;
   private BigDecimal creditAmount;
   private BigDecimal balance;
   private BigDecimal frozenBalance;
 
   public BigDecimal getFrozenBalance()
   {
     return this.frozenBalance;
   }
 
   public void setFrozenBalance(BigDecimal frozenBalance) {
     this.frozenBalance = frozenBalance;
   }
 
   public String getId() {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public Date getSummaryDate() {
     return this.summaryDate;
   }
 
   public void setSummaryDate(Date summaryDate) {
     this.summaryDate = summaryDate;
   }
 
   public String getBookCode() {
     return this.bookCode;
   }
 
   public void setBookCode(String bookCode) {
     this.bookCode = bookCode;
   }
 
   public String getBookName() {
     return this.bookName;
   }
 
   public void setBookName(String bookName) {
     this.bookName = bookName;
   }
 
   public BigDecimal getDebitAmount() {
     return this.debitAmount;
   }
 
   public void setDebitAmount(BigDecimal debitAmount) {
     this.debitAmount = debitAmount;
   }
 
   public BigDecimal getCreditAmount() {
     return this.creditAmount;
   }
 
   public void setCreditAmount(BigDecimal creditAmount) {
     this.creditAmount = creditAmount;
   }
 
   public BigDecimal getBalance() {
     return this.balance;
   }
 
   public void setBalance(BigDecimal balance) {
     this.balance = balance;
   }
 }