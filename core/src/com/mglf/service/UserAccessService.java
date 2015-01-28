package com.mglf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.dao.UserAccessMapper;
import com.mglf.entity.UserAccess;

@Service
@Transactional
public class UserAccessService {

	@Autowired
	private UserAccessMapper userAccessMapper;

	@Transactional(readOnly = false)
	public void insert(UserAccess userAccess) {
		userAccessMapper.insert(userAccess);
	}
	
	
}
