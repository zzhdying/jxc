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

import com.mglf.dao.PrdMapper;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Prd;
import com.mglf.entity.User;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class PrdService {
	
	@Autowired
	private PrdMapper prdMapper;
	
	/**
	 * 取最大番号
	 * @author zhongzhuohan
	 * @param entid
	 * @return
	 */
	@Transactional(readOnly=true)
	public String getMaxNum(String entid){
		Prd prd = prdMapper.selectMaxNum(entid);
		if(EmptyUtil.isEmpty(prd)){
			return "p0000001";
		}
		int n = Integer.parseInt(prd.getNum().substring(1)) + 10000000;
		return "p" + String.valueOf(++n).substring(1);		
	}
	
	
	/**
	 * 添加产品
	 * @author zhongzhuohan
	 * @param prd
	 */
	@Transactional(readOnly=false)
	public void add(Prd prd){
		prd.setId(UUID.randomUUID().toString());
		prd.setNum(getMaxNum(SpringSecurityUtils.getLoginUser().getEntid()));
		prd.setEntid(SpringSecurityUtils.getLoginUser().getEntid());
		prd.setCreateTime(new Date());
		prd.setCreateUser(SpringSecurityUtils.getLoginUser().getUserId());
		prdMapper.insert(prd);
	}
	
	/**
	 * 
	 * @author zhongzhuohan
	 * @param prd
	 */
	@Transactional(readOnly=false)
	public void update(Prd prd){
		prd.setUpdateTime(new Date());
		prd.setUpdateUser(SpringSecurityUtils.getLoginUser().getUserId());
		prdMapper.updateByPrimaryKeySelective(prd);
	}
	
	/**
	 * 删除商品
	 * @author zhongzhuohan
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void removePrd(String id){
		prdMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 取企业下所有的产品
	 * @author zhongzhuohan
	 * @param entid
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Prd> getEntAllPrds(String entid){
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("entid", entid);
		return prdMapper.selectPrdBy(map);
	}
	
	
	/**
	 * 通过Id取产品
	 * @author zhongzhuohan
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Prd getPrdByid(String id){
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Prd> prdList = prdMapper.selectPrdBy(map);
		if(EmptyUtil.isEmpty(prdList)){
			return null;
		}
		return prdList.get(0);
	}
	
	/**
	 * 通过Id取产品
	 * @author zhongzhuohan
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Prd getPrdByNum(String num){
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("num", num);
		List<Prd> prdList = prdMapper.selectPrdBy(map);
		if(EmptyUtil.isEmpty(prdList)){
			return null;
		}
		return prdList.get(0);
	}
}
