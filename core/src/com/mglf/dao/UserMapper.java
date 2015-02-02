package com.mglf.dao;

import java.util.Map;

import com.mglf.entity.User;

public interface UserMapper {
	
	public User selectByPrimaryKey(String id);
	
	public int deleteByPrimaryKey(String id);
	
	public int insert(User user);
	
	public int updateByPrimaryKeySelective(User user);
	
	public int updateByPrimaryKey(User user);
	
    public User selectUserBy(Map<String,Object> map);
}