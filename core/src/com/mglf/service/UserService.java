package com.mglf.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.dao.UserMapper;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.User;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 通过ID取用户
	 * @author zhongzhuohan
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public User getUserByid(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		return userMapper.selectUserBy(map);
	}
	
	public User doLogin(String username , String password, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", password);
		User user = userMapper.selectUserBy(map);
		if(EmptyUtil.isEmpty(user)){
			return null;
		}
		UserDetails userDetails = new UserDetails();
		userDetails.setUser(user);
		SpringSecurityUtils.saveUserDetailsToContext(userDetails, request);
				
		return user;
	}
}
