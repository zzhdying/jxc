package com.mglf.dao;

import com.mglf.entity.ListItem;

public interface ListItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(ListItem record);

    int insertSelective(ListItem record);

    ListItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ListItem record);

    int updateByPrimaryKey(ListItem record);
}