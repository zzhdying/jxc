package com.mglf.dao;

import com.mglf.entity.List;

public interface ListMapper {
    int deleteByPrimaryKey(String id);

    int insert(List record);

    int insertSelective(List record);

    List selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(List record);

    int updateByPrimaryKey(List record);
}