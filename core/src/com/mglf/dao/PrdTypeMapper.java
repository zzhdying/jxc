package com.mglf.dao;

import com.mglf.entity.PrdType;

public interface PrdTypeMapper {
	public int deleteByPrimaryKey(String id);

	public int insert(PrdType record);

	public PrdType selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(PrdType record);

	public int updateByPrimaryKey(PrdType record);
}