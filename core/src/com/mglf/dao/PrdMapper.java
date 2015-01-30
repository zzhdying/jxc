package com.mglf.dao;

import com.mglf.entity.Prd;

public interface PrdMapper {
    int deleteByPrimaryKey(String id);

    int insert(Prd record);

    int insertSelective(Prd record);

    Prd selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Prd record);

    int updateByPrimaryKeyWithBLOBs(Prd record);

    int updateByPrimaryKey(Prd record);
}