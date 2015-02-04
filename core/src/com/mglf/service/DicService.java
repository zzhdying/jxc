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
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Dic;
import com.mglf.entity.Prd;
import com.mglf.entity.User;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class DicService {
	@Autowired
	private DicMapper dicMapper;
	
	/**
	 * 通过ID找字典数据
	 * @author zhongzhuohan
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Dic getDicById(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Dic> dicList = dicMapper.selectBy(map);
		if(EmptyUtil.isEmpty(dicList)){
			return null;
		}
		return dicList.get(0);
	}
	
	/**
	 * 通过编码找字典
	 * @author zhongzhuohan
	 * @param code
	 * @param entid
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Dic> getDicByCode(String code , String entid){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("entid", entid);
		List<Dic> dicList = dicMapper.selectBy(map);
		return dicList;
	}
	
	/**
	 * 添加字典
	 * @author zhongzhuohan
	 * @param dic
	 */
	@Transactional(readOnly=false)
	public void add(Dic dic){
		dic.setId(UUID.randomUUID().toString());
		dic.setEntid(SpringSecurityUtils.getLoginUser().getEntid());
		dic.setCreateUser(SpringSecurityUtils.getLoginUser().getUserId());
		dic.setCreateTime(new Date());
		dicMapper.insert(dic);
	}
	
	/**
	 * 更新字典
	 * @author zhongzhuohan
	 * @param dic
	 */
	@Transactional(readOnly=false)
	public void update(Dic dic){
		dic.setUpdateTime(new Date());
		dic.setUpdateUser(SpringSecurityUtils.getLoginUser().getUserId());
		dicMapper.updateByPrimaryKeySelective(dic);
	}
	
	/**
	 * 更新字典
	 * @author zhongzhuohan
	 * @param dic
	 */
	@Transactional(readOnly=false)
	public void remove(String id){
		dicMapper.deleteByPrimaryKey(id);
	}
}
