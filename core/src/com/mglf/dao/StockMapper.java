package com.mglf.dao;

import com.mglf.entity.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(String id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}