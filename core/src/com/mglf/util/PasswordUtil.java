package com.mglf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mglf.util.ConfigUtil;
import com.mglf.util.PasswordUtil;

import com.mglf.util.ConfigUtil;
import com.mglf.util.PasswordUtil;
/**
 * Java常用的对密码加密的方法
 * 对摘要信息进行加密编码
 */
public class PasswordUtil {
	
	private final static Log log = LogFactory.getLog(PasswordUtil.class);
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	/**
	 * 将字节数组转换为16进制的字符串
	 * @param byteArray 字节数组
	 * @return 16进制的字符串
	 */
	private static String byteArrayToHexString(byte[] byteArray){
		StringBuffer sb = new StringBuffer();
		for(byte byt:byteArray){
			sb.append(byteToHexString(byt));
		}
		return sb.toString();
	}
	/**
	 * 将字节转换为16进制字符串
	 * @param byt 字节
	 * @return 16进制字符串
	 */
	private static String byteToHexString(byte byt) {
		int n = byt;
		if (n < 0)
			n = 256 + n;
		return hexDigits[n/16] + hexDigits[n%16];
	}
	/**
	 * 将摘要信息转换为相应的编码
	 * @param code 编码类型
	 * @param message 摘要信息
	 * @return 相应的编码字符串
	 */
	private static String encode(String code,String message){
		MessageDigest md;
		String encode = null;
		try {
			md = MessageDigest.getInstance(code);
			encode = byteArrayToHexString(md.digest(message
					.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			log.error("encode", e);
		}
		return encode;
	}
	/**
	 * 将摘要信息转换成MD5编码
	 * @param message 摘要信息
	 * @return MD5编码之后的字符串
	 */
	public static String md5Encode(String message){
		return encode("MD5",message);
	}
	/**
	 * 将摘要信息转换成SHA编码
	 * @param message 摘要信息
	 * @return SHA编码之后的字符串
	 */
	public static String shaEncode(String message){
		return encode("SHA",message);
	}
	/**
	 * 将摘要信息转换成SHA-256编码
	 * @param message 摘要信息
	 * @return SHA-256编码之后的字符串
	 */
	public static String sha256Encode(String message){
		return encode("SHA-256",message);
	}
	/**
	 * 将摘要信息转换成SHA-512编码
	 * @param message 摘要信息
	 * @return SHA-512编码之后的字符串
	 */
	public static String sha512Encode(String message){
		return encode("SHA-512",message);
	}
	
	/**
	 * 用户密码加严方法
	 * @param id 用户ID
	 * @param password 用户密码
	 * @return
	 */
	public static String encodePassword(String id, String password){
		StringBuilder str=new StringBuilder();
		str.append(password);
		str.append("{");
		str.append(id);
		str.append(ConfigUtil.readSysValue("pwdRandom"));
		str.append("}");
		return md5Encode(str.toString());
	}
	
	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("args:random userid password");
			return;
		}
		
		String random = args[0];
		String id = args[1];
		String passwd = args[2];
		
		StringBuilder str=new StringBuilder();
		str.append(passwd);
		str.append("{");
		str.append(id);
		str.append(random);
		str.append("}");

		System.out.println( md5Encode(str.toString()));
	}
	
}
