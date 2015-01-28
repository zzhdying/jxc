package com.mglf.dao;

import com.mglf.base.BaseMapper;
import com.mglf.entity.UserAccess;

/**
 * 访问记录Mapper
 * 
 * @author: benz
 */
public interface UserAccessMapper extends BaseMapper {

	public void insert(UserAccess userAccess);

}