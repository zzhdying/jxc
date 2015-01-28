/*     */ package com.fescoadecco.weixin.dto.msg;
/*     */ 
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ public class Msg4Voice extends Msg
/*     */ {
/*     */   private String mediaId;
/*     */   private String format;
/*     */   private String recognition;
/*     */   private String msgId;
/*     */ 
/*     */   public Msg4Voice()
/*     */   {
/*  34 */     this.head = new Msg4Head();
/*  35 */     this.head.setMsgType("voice");
/*     */   }
/*     */ 
/*     */   public Msg4Voice(Msg4Head head)
/*     */   {
/*  40 */     this.head = head;
/*     */   }
/*     */ 
/*     */   public void write(Document document)
/*     */   {
/*  46 */     Element root = document.createElement("xml");
/*  47 */     this.head.write(root, document);
/*  48 */     Element voiceElement = document.createElement("Voice");
/*  49 */     Element mediaIdElement = document.createElement("MediaId");
/*  50 */     voiceElement.appendChild(mediaIdElement);
/*  51 */     root.appendChild(voiceElement);
/*  52 */     document.appendChild(root);
/*     */   }
/*     */ 
/*     */   public void read(Document document)
/*     */   {
/*  58 */     this.mediaId = getElementContent(document, "MediaId");
/*  59 */     this.format = getElementContent(document, "Format");
/*  60 */     this.recognition = getElementContent(document, "Recognition");
/*  61 */     this.msgId = getElementContent(document, "MsgId");
/*     */   }
/*     */ 
/*     */   public String getMediaId()
/*     */   {
/*  69 */     return this.mediaId;
/*     */   }
/*     */ 
/*     */   public void setMediaId(String mediaId)
/*     */   {
/*  77 */     this.mediaId = mediaId;
/*     */   }
/*     */ 
/*     */   public String getFormat()
/*     */   {
/*  85 */     return this.format;
/*     */   }
/*     */ 
/*     */   public void setFormat(String format)
/*     */   {
/*  93 */     this.format = format;
/*     */   }
/*     */ 
/*     */   public String getRecognition()
/*     */   {
/* 101 */     return this.recognition;
/*     */   }
/*     */ 
/*     */   public void setRecognition(String recognition)
/*     */   {
/* 109 */     this.recognition = recognition;
/*     */   }
/*     */ 
/*     */   public String getMsgId()
/*     */   {
/* 117 */     return this.msgId;
/*     */   }
/*     */ 
/*     */   public void setMsgId(String msgId)
/*     */   {
/* 125 */     this.msgId = msgId;
/*     */   }
/*     */ }
