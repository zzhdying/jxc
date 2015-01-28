package com.mglf.util;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;

import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;

/**
 * memcached 工具类
 * @author zhongzhuohan
 *
 */
public class CacheUtil {

	private final static Log log = LogFactory.getLog(CacheUtil.class);
	
	private static MemcachedClient memClient = null;

	public static int DEFAULT_TIMEOUT = 5;

	public static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;

	public static int DEFAULT_EXPIRE = 60*60*10;	//一小時
	
	public static int GROUP_DICT = 0 ;
	public static int GROUP_JOB = 1 ;
	public static int GROUP_WEIXIN_REGLOGIN = 2 ;
	public static int GROUP_LOGIN_CHECK = 3 ;
	public static int GROUP_USER_ACT = 4 ;
	public static int GROUP_CAPTCHA = 5 ;
	public static int GROUP_NUM_SEQ = 6 ; //recommend saved seq
	public static int GROUP_MOBILEMSG_DELAY = 7 ;
	public static int GROUP_WEIXIN_KEY = 8 ;
	public static int GROUP_ENT_STATE = 9 ;
	public static int GROUP_PER_STATE = 10 ;
	public static int GROUP_PER_USERID = 11 ;
	public static int GROUP_ADDUSER_FRIEND = 12 ;
	public static int GROUP_IMPORT_RESUME = 13;	//added by jinxiaochen
	public static int GROUP_IMPORT_JOB = 14;	//added by zhanghuichun
	public static int GROUP_ADMIN_TASK=15;
	public static int GROUP_SHORT_URL=16;
	public static int GROUP_ADMIN_RANK = 17;
	public static int GROUP_FIND_PWD = 18; //added by wengxk
	public static int GROUP_WEB_TOMOBILE = 19; //added by DANEL
	public static int GROUP_EMAIL_ACTIVE = 20;//added by zhong
	public static int GROUP_MESSAGE = 21;
	public static int GROUP_SIMPLE_RESUME_ACTIVE = 22;//简易简历应聘   added by jinxiaochen
	public static int GROUP_AUTH = 23;
	public static int GROUP_ADDJOBDTO = 24;  //mobile发布职位模版
	
	public static int[] groupExpire = new int[]{
		60*60,	//一小時
		60*60,	//一小時
		10*60,	//十分钟
		15*60,	//十五分钟
		30*24*30*60,	//三十分钟
		10*60,	//十分钟
		30*24*60*60,//一天
		60,	//一分钟
		60*60,	//一小時
		60*60,	//一小時
		60*60,	//一小時
		10*60,    //十分钟
		2*24*60*60,//一星期  推荐用
		60*60,	//一小时,added by jinxiaochen
		10*60,	//added by zhanghuichun
		1*60,	//1分钟
		24*60*60,//一天
		8*60*60,//8小时
		2*60*60,//2小时 //added by wengxk
		5*60,// WEB跳转手机登陆
		7*24*60*60,//一星期  added by zhong
		60,	//一分钟
		48*60*60,	//48小时 added by jinxiaochen
		24*60*60,//一天
		30*24*60*60 //一个月
	};
	
	public static String[] groupPrefix = new String[]{
		"dict_",
		"job_",
		"weixin_reglogin_",
		"login_check_",
		"user_act_",
		"captcha_",
		"num_seq_",
		"mobilemsg_delay_",
		"weixin_key_",
		"ent_state_",
		"per_state_",
		"per_userId_",
		"per_addUser_friend_",
		"per_import_resume_",	//added by jinxiaochen
		"ent_import_job_",	//added by zhanghuichun
		"admin_task_",
		"short_url_",
		"admin_rank_",
		"ent_find_pwd_", //added by wengxk
		"web_to_mobile_", //added by wengxk
		"per_email_active", //added by zhong
		"message_",
		"simple_resume_active", //added by jinxiaochen
		"auth_",
		"addjobdto_"
	};
	
	
	
	private static void connect() throws IOException {
		if (memClient == null) { 
			memClient = new MemcachedClient(
					AddrUtil.getAddresses(ConfigUtil.readSysValue("MemcachedAddresses")));
		}
	}

	private static void disconnect() {
		if (memClient != null) {
			memClient.shutdown();
		}
	}

	private static boolean getBooleanValue(Future<Boolean> f) {
		try {
			Boolean bool = f.get(DEFAULT_TIMEOUT, DEFAULT_TIMEUNIT);
			return bool.booleanValue();
		} catch (Exception e) {
			f.cancel(false);
			return false;
		}
	}

	
	public static CASValue getCAS(int group, String key) throws IOException {
		connect();
		
		key = groupPrefix[group]+key;
		return memClient.gets(key);
	}
	
	public static boolean setValueCAS(int group, String key, Object value, CASValue cas) throws IOException {
		connect();
		
		key = groupPrefix[group]+key;
		
		if(cas != null){
			CASResponse res = memClient.cas(key, cas.getCas(), value);
			if(CASResponse.OK == res){
				return true;
			}
			
			if(CASResponse.NOT_FOUND != res){
				return false;
			}
		}
		
		Future<Boolean> f = memClient.add(key, groupExpire[1], value);
		return getBooleanValue(f);
	}
	
	
	/**
	 * 异步保存
	 * @param group 分组
	 * @param key 键
	 * @param value 值
	 * @return
	 * @throws IOException
	 */
	public static Future<Boolean> asyncSave(int group, String key, Object value)
			throws IOException {
		connect();

		key = groupPrefix[group]+key;
		return memClient.set(key, groupExpire[group], value);
	}

	/**
	 * 同步保存
	 * @param group 分组
	 * @param key 键
	 * @param value 值
	 * @return
	 * @throws IOException
	 */
	public static boolean save(int group, String key, Object value, int expire)
			throws IOException {
		connect();

		key = groupPrefix[group]+key;
		return getBooleanValue(memClient.set(key, expire, value));
	}
	
	/**
	 * 同步保存
	 * @param group 分组
	 * @param key 键
	 * @param value 值
	 * @return
	 * @throws IOException
	 */
	public static boolean save(int group, String key, Object value)
			throws IOException {
		Future<Boolean> f = asyncSave(group, key, value);
		return getBooleanValue(f);
	}
	
	/**
	 * 获取值
	 * @param group 分组
	 * @param key 键
	 * @return
	 * @throws IOException
	 */
	public static Object load(int group, String key) throws IOException {
		connect();

		for(int i=0; i<3; i++){
			key = groupPrefix[group]+key;
			try{
				return memClient.get(key);
			}catch(CancellationException ce){
				continue;
			}catch(Exception ote){
				log.error("load:"+key, ote);
				return null;
			}			
		}

		return null;
	}
	
	public static void delete(int group,String key) throws IOException {
		connect();
		
		key = groupPrefix[group]+key;
		memClient.delete(key);
	}

	/**
	 * 批量获取值
	 * @param group 分组
	 * @param keys 键数组
	 * @return
	 * @throws IOException
	 */
	public static Object[] load(int group, String[] keys) throws IOException {
		connect();

		for(int i=0; i<keys.length; i++){
			keys[i] = groupPrefix[group]+keys[i];
		}
		
		try{
			return memClient.getBulk(keys).values().toArray();
		}catch(Exception ote){
			log.error("load:"+keys, ote);
			return new Object[0];
		}
	}
	
//	public static String[] getKeyList() throws IOException {
//		connect();		
//		return null;
//	}
	
	// test
	public static void main(String[] argvs) {
		System.out.println("CacheUtil Test");

		try {
			
			String id = null;
			
			while(id != null){
				CASValue cv = getCAS(CacheUtil.GROUP_NUM_SEQ, "TABLE_NAME_AND_FIELD_NAME");
				
				if(cv == null){
					//from db
				}else{
					//id = cv.getValue() + 1;
				}
								
				boolean f = setValueCAS(CacheUtil.GROUP_NUM_SEQ, "TABLE_NAME_AND_FIELD_NAME", id, cv);
				
				if(f){
					break;
				}
			}

			
			
			save(GROUP_DICT, "test1", "123456789");
			save(GROUP_DICT, "test2", "123456789");

			System.out.println(load(GROUP_DICT, "test1"));
			
			Object[] values = load(GROUP_DICT, new String[]{"test1","test2"});
			for(int i=0; i<values.length; i++){
				System.out.println(values[i]);
			}
			
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
