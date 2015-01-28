package com.mglf.dto;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.mglf.dao.interceptor.SearchPageableCondition;

public class SolrCondition extends SearchPageableCondition {
	
	public static String COLLECTION_JOB = "collection_job";
	
	
	private String collection;
	
	private Map<Object,String> condition;
	
	private Class resultDto;
	
	public SolrCondition(){
		condition = new HashMap<Object,String>();
	}
	
	
	public SolrCondition(String collection){
		condition = new HashMap<Object,String>();
		this.collection = collection;
	}
	
	
	public String getCollection() {
		return collection;
	}




	public void setCollection(String collection) {
		this.collection = collection;
	}




	public Class getResultDto() {
		return resultDto;
	}



	public void setResultDto(Class resultDto) {
		this.resultDto = resultDto;
	}



	public void add(String key, String val){
		condition.put(key, val);
	}
	
	
	public void add(String[] keys, String val){
		condition.put(keys, val);
	}
	
	public String getQueryStr() throws Exception{
		String ret = "";
		
		for(Object key : condition.keySet()){
			String val = condition.get(key);
			
			val = URLEncoder.encode(val, "UTF8");
			
			if(!"".equals(ret)){
				ret += " and ";
			}
			
			if(key instanceof String[]){
				ret += " ( ";
				
				String[] keys = (String[])key;
				for(int i=0; i<keys.length; i++){
					if(i>0){
						ret += " or ";
					}
					
					ret += keys[i] + ":" + val;
				}
				
				ret += " ) ";
			}else{
				ret += key + ":" + val;
			}
		}
		
		return ret;
	}
	
}
