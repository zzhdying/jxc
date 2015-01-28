package com.mglf.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.mglf.base.AppException;
import com.mglf.mvc.NonHtmlEscape;

@Aspect
public class LogInterceptor {
	
	static Logger logger = Logger.getLogger(LogInterceptor.class);
	
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal<Class> webRetType = new ThreadLocal<Class>();
	
	private static final ThreadLocal<Boolean> webRetNonHtmlEscape = new ThreadLocal<Boolean>();
	
	public static final ThreadLocal<String> webIp = new ThreadLocal<String>();
	
	@SuppressWarnings("rawtypes")
	public static void setWebRetType(Class type) {
		webRetType.set(type);
	}

	@SuppressWarnings("rawtypes")
	public static Class getWebRetType() {
		return webRetType.get();
	}
	
	
	public static void setRetNonHtmlEscape(boolean val) {
		webRetNonHtmlEscape.set(val);
	}

	public static boolean getRetNonHtmlEscape() {
		Boolean ret = webRetNonHtmlEscape.get();
		if(ret == null){
			return false;
		}
		return ret.booleanValue();
	}
	
	
	
	@Pointcut("execution(* com.mglf.*.web.*.*.*(..))")
	public void logPointcut() {}

	@Around("com.mglf.interceptor.LogInterceptor.logPointcut()")
	public Object logWrite(ProceedingJoinPoint pjp) throws Throwable {
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		String ip = webIp.get();
		if(ip == null){
			ip = "";
		}
		
		for (Object object : pjp.getArgs()) {
			if(object instanceof HttpServletRequest){
				continue;
			}
			if(object instanceof HttpServletResponse){
				continue;
			}
			
			if(object!=null){
				try {
					jsonObject = JSONObject.fromObject(object);
					jsonArray.add(jsonObject);
				} catch (Exception e) {
					jsonArray.add(object.toString());
				}
			}
		}
				
		logger.info("ip:"+ip+",class:" + pjp.getSignature().getDeclaringType().getName()
				+ ",method:" + pjp.getSignature().getName() + ",args:"
				+ jsonArray.toString());
		
		Method method = null;
		for(Method m : pjp.getSignature().getDeclaringType().getMethods()){
			if(m.getName().equals(pjp.getSignature().getName())){
				method = m;
				break;
			}
		}
		
		setRetNonHtmlEscape(method.isAnnotationPresent(NonHtmlEscape.class));
		
		try{
			return pjp.proceed();
		}catch(Exception e){
			
			logger.error("ip:"+ip+"," + e, e);	
						
			if(method != null){
				setWebRetType(method.getReturnType());
			}else{
				setWebRetType(null);
			}
			
			throw e;
		}
	}
}
