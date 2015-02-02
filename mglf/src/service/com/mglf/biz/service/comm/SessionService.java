package com.mglf.biz.service.comm;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.biz.dao.comm.SessionMapper;
import com.mglf.dto.UserDetails;
import com.mglf.entity.User;
import com.mglf.util.AppConstants;

/**
 * 用户会话相关操作的service 实现spring security UserDetailsService接口
 * 
 * @author zhuben
 * 
 */
@Component
@Transactional
public class SessionService implements UserDetailsService {

	private final static Log log = LogFactory.getLog(SessionService.class);
	
	@Autowired
	private SessionMapper sessionMapper;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) {
		try {
			User perUser = sessionMapper.loadUserByUsername(userName);
			if (perUser == null) {
				return null;
			}
			UserDetails pud = new UserDetails();
			pud.setUser(perUser);
			return pud;
		} catch (Exception e) {
			log.error(log);
		}
		return null;
	}
	
}
