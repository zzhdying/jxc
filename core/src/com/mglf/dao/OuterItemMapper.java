package com.mglf.dao;

import com.mglf.entity.OuterItem;

public interface OuterItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(OuterItem record);

    int insertSelective(OuterItem record);

    OuterItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OuterItem record);

    int updateByPrimaryKey(OuterItem record);
}