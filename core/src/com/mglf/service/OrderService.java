package com.mglf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.dao.DicMapper;
import com.mglf.dao.OrderMapper;
import com.mglf.dao.PrdMapper;
import com.mglf.dao.SupplierMapper;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Dic;
import com.mglf.entity.Order;
import com.mglf.entity.Prd;
import com.mglf.entity.Supplier;
import com.mglf.entity.User;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	/**
	 * 取最大番号
	 * @author zhongzhuohan
	 * @param entid
	 * @return
	 */
	@Transactional(readOnly=true)
	public String getMaxNum(String entid){
		Order supplier = orderMapper.selectMaxNum(entid);
		if(EmptyUtil.isEmpty(supplier)){
			return "o0000001";
		}
		int n = Integer.parseInt(supplier.getNum().substring(1)) + 10000000;
		return "o" + String.valueOf(++n).substring(1);		
	}
	
	@Transactional(readOnly=true)
	public Order getByid(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Order> list = orderMapper.selectBy(map);
		if(EmptyUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	@Transactional(readOnly=true)
	public List<Order> getByEntid(String entid){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("entid", entid);
		return orderMapper.selectBy(map);
	}
	
	@Transactional(readOnly=false)
	public Order add(Order order){
		order.setId(UUID.randomUUID().toString());
		order.setEntid(SpringSecurityUtils.getLoginUser().getEntid());
		order.setNum(getMaxNum(SpringSecurityUtils.getLoginUser().getEntid()));
		order.setCreateUser(SpringSecurityUtils.getLoginUser().getUserId());
		order.setCreateTime(new Date());
		order.setIsOk(Order.NOT_OK);
		order.setPrdCount(BigDecimal.ZERO);
		order.setBuyPrice(BigDecimal.ZERO);
		order.setSellPrice(BigDecimal.ZERO);
		orderMapper.insert(order);
		return order;
	}
	
	@Transactional(readOnly=false)
	public void update(Order order){
		order.setUpdateUser(SpringSecurityUtils.getLoginUser().getUserId());
		order.setUpdateTime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
	}
	
	@Transactional(readOnly=false)
	public void remove(String id){
		orderMapper.deleteByPrimaryKey(id);
	}
}
