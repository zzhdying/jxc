package com.mglf.biz.dao.comm;

import com.mglf.entity.User;



/**
 * 用户会话相关操作的dao
 * @author zhuben
 *
 */
public interface SessionMapper  {
	
	/**
	 * 
	 * @param userName 根据用户名搜索
	 * @return
	 */
	public User loadUserByUsername(String userName);
	
}
