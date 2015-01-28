package com.mglf.dao;

import com.mglf.entity.PrdType;

public interface PrdTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrdType record);

    int insertSelective(PrdType record);

    PrdType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrdType record);

    int updateByPrimaryKeyWithBLOBs(PrdType record);

    int updateByPrimaryKey(PrdType record);
}