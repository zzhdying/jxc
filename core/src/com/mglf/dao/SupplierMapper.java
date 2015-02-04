package com.mglf.dao;

import java.util.List;
import java.util.Map;

import com.mglf.entity.Supplier;

public interface SupplierMapper {
   
	public int deleteByPrimaryKey(String id);

	public int insert(Supplier record);

	public List<Supplier> selectBy(Map<String,Object> map);

	public int updateByPrimaryKeySelective(Supplier record);

	public int updateByPrimaryKey(Supplier record);
	
	public Supplier selectMaxNum(String entid);
}