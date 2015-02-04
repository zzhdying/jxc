package com.mglf.service;

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
import com.mglf.dao.PrdMapper;
import com.mglf.dao.SupplierMapper;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Dic;
import com.mglf.entity.Prd;
import com.mglf.entity.Supplier;
import com.mglf.entity.User;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class SupplierService {
	
	@Autowired
	private SupplierMapper supplierMapper;
	/**
	 * 取最大番号
	 * @author zhongzhuohan
	 * @param entid
	 * @return
	 */
	@Transactional(readOnly=true)
	public String getMaxNum(String entid){
		Supplier supplier = supplierMapper.selectMaxNum(entid);
		if(EmptyUtil.isEmpty(supplier)){
			return "s0000001";
		}
		int n = Integer.parseInt(supplier.getNum().substring(1)) + 10000000;
		return "s" + String.valueOf(++n).substring(1);		
	}
	
	@Transactional(readOnly=true)
	public Supplier getSupplierByid(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Supplier> list = supplierMapper.selectBy(map);
		if(EmptyUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	@Transactional(readOnly=true)
	public List<Supplier> getSupplierByEntid(String entid){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("entid", entid);
		return supplierMapper.selectBy(map);
	}
	
	@Transactional(readOnly=false)
	public void add(Supplier supplier){
		supplier.setId(UUID.randomUUID().toString());
		supplier.setEntid(SpringSecurityUtils.getLoginUser().getEntid());
		supplier.setNum(getMaxNum(SpringSecurityUtils.getLoginUser().getEntid()));
		supplier.setCreateUser(SpringSecurityUtils.getLoginUser().getUserId());
		supplier.setCreateDate(new Date());
		supplierMapper.insert(supplier);
	}
	
	@Transactional(readOnly=false)
	public void update(Supplier supplier){
		supplier.setUpdateUser(SpringSecurityUtils.getLoginUser().getUserId());
		supplier.setUpdateDate(new Date());
		supplierMapper.updateByPrimaryKeySelective(supplier);
	}
	
	@Transactional(readOnly=false)
	public void remove(String id){
		supplierMapper.deleteByPrimaryKey(id);
	}
}
