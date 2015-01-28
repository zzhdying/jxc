package com.mglf.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mglf.util.ConfigUtil;
import com.mglf.util.StringUtil;

import com.mglf.util.ConfigUtil;
import com.mglf.util.StringUtil;
/**
 * 读取config
 * @author xpc
 *
 */
public class ConfigUtil {
	static Logger logger = Logger.getLogger(ConfigUtil.class);
	
	public final static String WEIXIN_PATH="/config/weixin.properties";
	
	public final static String SYS_PATH="/config/sys.properties"; 
	
	public static Map<String,  Properties> configCache = new HashMap<String,  Properties>();
	
	/**
	 * 读取value
	 * @param filePath
	 * @param key
	 * @return
	 */
	private static String readValue(String filePath, String key) {
		String value=null;
		if (StringUtil.isBlank(filePath) || StringUtil.isBlank(key)) {
			return "";
		}
		Properties p = configCache.get(filePath);
		if(p == null){
			try {
				p=new Properties();
				InputStream inputStream = ConfigUtil.class.getResourceAsStream(filePath);
				p.load(inputStream);
				inputStream.close();
				value = p.getProperty(key);
			} catch (Exception e) {
				logger.error("load "+filePath+" error");
			}
		}
		
		if(value == null){
			return "";
		}
		return value;
	}
	
	public static String readSysValue(String key){
		return readValue(SYS_PATH, key);
	}
	
	/**
	 * 读取微信value
	 * @param key
	 * @return
	 */
	public static String readWeixinValue(String key){
		return readValue(WEIXIN_PATH, key);
	}
	
	public static String getValue(String filePath,String key){
		return readValue(filePath, key.trim());
	}
}
