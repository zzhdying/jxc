package com.mglf.util;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonUtil {
	
	private static Map<Class, Map<String, Method>> jsonToBeanCache = new HashMap<Class, Map<String, Method>>();
	
	
	public static Object jsonToBean(JSONObject json, Class cls){
		Object ret = JSONObject.toBean(json, cls);
		
		try{
			Map<String, Method> m = jsonToBeanCache.get(cls);
			if(m == null){
				synchronized(jsonToBeanCache){
					m = jsonToBeanCache.get(cls);
					
					if(m == null){
						m = new HashMap<String, Method>();
						Method[] methods = cls.getMethods();
						for(int i=0; i<methods.length; i++){
							Method method = methods[i];
							String methodName = method.getName();
							
							if(!methodName.startsWith("set")){
								continue;
							}
							
							if(method.getParameterTypes() == null){
								continue;
							}
							
							if(method.getParameterTypes().length != 1){
								continue;
							}
							
							if(method.getParameterTypes()[0] != Date.class){
								continue;
							}
							
							String name = methodName.substring(3);
							name = name.substring(0,1).toLowerCase()+name.substring(1);
						
							m.put(name, method);
						}
						
						jsonToBeanCache.put(cls, m);
					}	
				}
			}

			Calendar cd = Calendar.getInstance();
			for(String name : m.keySet()){
				String v = json.getString(name);
				
				if(v != null && !"null".equals(v)){
					cd.setTimeInMillis(Long.parseLong(v));
					
					m.get(name).invoke(ret, new Object[]{cd.getTime()});
				}
			}
			
		}catch(Exception e){
		}

		return ret;
	}
}
