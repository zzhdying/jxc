 package com.mglf.finance.util;
 
 import com.mglf.base.AppException;
 import java.io.InputStream;
 import java.io.ObjectInputStream;
 import java.security.PrivateKey;
 import java.security.PublicKey;
 import java.security.Signature;
 import org.apache.commons.httpclient.NameValuePair;
 import sun.misc.BASE64Decoder;
 import sun.misc.BASE64Encoder;
 
 public class SignUtil
 {
   private static PrivateKey prikey = null;
 
   private static PublicKey pubkey = null;
 
   public static NameValuePair[] sign(NameValuePair[] nvps) throws Exception
   {
     NameValuePair[] ret = new NameValuePair[nvps.length + 1];
     String dat = "";
 
     for (int i = 0; i < nvps.length; i++) {
       ret[i] = nvps[i];
       dat = dat + ":" + nvps[i].getValue();
     }
 
     if (prikey == null) {
       InputStream inputStream = SignUtil.class.getResourceAsStream("/config/fin-prikey.dat");
       ObjectInputStream in = new ObjectInputStream(inputStream);
 
       prikey = (PrivateKey)in.readObject();
       in.close();
     }
 
     Signature signet = 
       Signature.getInstance("DSA");
     signet.initSign(prikey);
     signet.update(dat.getBytes("UTF8"));
     byte[] signed = signet.sign();
 
     ret[nvps.length] = new NameValuePair("secret", new BASE64Encoder().encode(signed));
 
     return ret;
   }
 
   public static void checkSign(String[] avgs, String signed) throws Exception
   {
     String dat = "";
 
     for (int i = 0; i < avgs.length; i++) {
       dat = dat + ":" + avgs[i];
     }
 
     if (pubkey == null) {
       InputStream inputStream = SignUtil.class.getResourceAsStream("/config/fin-pubkey.dat");
       ObjectInputStream in = new ObjectInputStream(inputStream);
       pubkey = (PublicKey)in.readObject();
       in.close();
     }
 
     Signature signetcheck = 
       Signature.getInstance("DSA");
     signetcheck.initVerify(pubkey);
     signetcheck.update(dat.getBytes("UTF8"));
     if (!signetcheck.verify(new BASE64Decoder().decodeBuffer(signed)))
       throw new AppException("签名异常");
   }
 
   public static void main(String[] args)
   {
     try
     {
       String dat = ":{\"optRecord\":{\"closeDatetime\":null,\"description\":\"应收款收到\",\"id\":\"\",\"optDatetime\":null,\"optStatus\":0,\"optType\":810310,\"optUserId\":\"adminuserid:12345\",\"optUserName\":\"admin_user_test\",\"optUserType\":100,\"refData\":\"offerid:123456\"},\"bookRecordList\":[{\"accountingDatetime\":null,\"amount\":20,\"batchId\":\"\",\"closeDatetime\":null,\"description\":\"\",\"entId\":\"ent:12345\",\"id\":\"\",\"optStatus\":0,\"optType\":0,\"optUserId\":\"\",\"optUserName\":\"\",\"optUserType\":0,\"refData\":\"\",\"targetBookRecord\":null,\"dtoClass\":\"com.mglf.finance.dto.book.EntReceivablesBookRecordDto\"},{\"accountName\":\"\",\"accountNo\":\"\",\"accountingDatetime\":null,\"amount\":-20,\"bank\":\"\",\"batchId\":\"\",\"charge\":false,\"closeDatetime\":null,\"description\":\"\",\"document\":\"\",\"id\":\"\",\"optStatus\":0,\"optType\":0,\"optUserId\":\"\",\"optUserName\":\"\",\"optUserType\":0,\"payAccountCode\":\"CMB_OFFLINE\",\"refData\":\"\",\"targetBookRecord\":null,\"dtoClass\":\"com.mglf.finance.dto.book.BankBookRecordDto\"}]}";
       String signal = "MC0CFQCQQivJp5AaoFz5y2XOE8o4g39BIgIUV9mleQalaY4eINhUZWO2SMrARFc=";
 
       checkSign(new String[] { dat }, signal);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }
 }