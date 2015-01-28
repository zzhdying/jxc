/**
 * 
 */
package com.mglf.util;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mglf.util.AppContextUtil;

import com.mglf.util.AppContextUtil;

/**
 * @author zhongzhuohan
 * @since 2011-5-7
 */
public class AppContextUtil {
	
	public static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/springmvc-servlet.xml");
	
	/**
	 * 测试用SpringContext，请勿直接使用
	 */
	public static Object getBean(String id){
		return context.getBean(id);
	}
	
	/**
	 * 测试用SpringContext，请勿直接使用
	 * @return 
	 */
	public static <T> T getBean(Class<T> manager){
		return context.getBean(manager);
	}
	
	/**
	 * 获取Message
	 */
	public static String getMessage(String key, Object[] params, Locale locale){
		return context.getMessage(key, params, locale);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(AppContextUtil.context.getMessage("welcome.label", null, Locale.CHINESE));
		System.out.println(AppContextUtil.context.getMessage("welcome.label", null, Locale.US));
		System.out.println(new Locale("zh").toString());
//		new Locale
		System.out.println(Locale.US);
//		for (String s :Locale.getISOLanguages()){
//			System.out.println(s);
//		}
	}

}
