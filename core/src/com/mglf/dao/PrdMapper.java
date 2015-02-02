package com.mglf.dao;

import java.util.List;
import java.util.Map;

import com.mglf.entity.Prd;

public interface PrdMapper {
	
    public int deleteByPrimaryKey(String id);

    public int insert(Prd record);

    public List<Prd> selectPrdBy(Map<String,Object> map);

    public int updateByPrimaryKeySelective(Prd record);

    public int updateByPrimaryKey(Prd record);
    
    public Prd selectMaxNum(String entid);
}