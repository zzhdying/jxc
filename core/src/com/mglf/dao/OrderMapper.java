package com.mglf.dao;

import java.util.List;
import java.util.Map;

import com.mglf.entity.Order;

public interface OrderMapper {
	
    public int deleteByPrimaryKey(String id);

    public int insert(Order record);

    public List<Order> selectBy(Map<String,Object> map);

    public int updateByPrimaryKeySelective(Order record);

    public int updateByPrimaryKey(Order record);
    
    public Order selectMaxNum(String entid);
}