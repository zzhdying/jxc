package com.mglf.dao;

import com.mglf.entity.OrderList;

public interface OrderListMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderList record);

    int insertSelective(OrderList record);

    OrderList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderList record);

    int updateByPrimaryKey(OrderList record);
}