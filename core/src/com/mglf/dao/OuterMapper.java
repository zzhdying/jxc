package com.mglf.dao;

import com.mglf.entity.Outer;

public interface OuterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Outer record);

    int insertSelective(Outer record);

    Outer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Outer record);

    int updateByPrimaryKey(Outer record);
}