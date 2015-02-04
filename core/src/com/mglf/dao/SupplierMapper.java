package com.mglf.dao;

import com.mglf.entity.Supplier;

public interface SupplierMapper {
   
	public int deleteByPrimaryKey(String id);

	public int insert(Supplier record);

	public Supplier selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(Supplier record);

	public int updateByPrimaryKey(Supplier record);
}