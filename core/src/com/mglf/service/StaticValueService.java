package com.mglf.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.dto.UserDetails;
import com.mglf.entity.Dic;
import com.mglf.util.SpringSecurityUtils;

@Service
@Transactional
public class StaticValueService {
	@Autowired
	private DicService dicService;
	
	@Transactional(readOnly=true)
	public void initStaticValueString(){
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		StringBuffer content = new StringBuffer();
		
		List<Dic> dicList = dicService.getDicByCode(null,loginUser.getEntid());
		String s = "var baseUnit=[''";
		for(Dic dic : dicList){		
			if(dic.getCode().equals(Dic.PRD_UNIT)){
				s += ",'" + dic.getNum()+" " + dic.getName()+"'";
			}
		}
		s += "];";
		content.append(s);
		
		s = "var baseOwnent=[''";
		for(Dic dic : dicList){		
			if(dic.getCode().equals(Dic.PRD_OWNENTNAME)){
				s += ",'" + dic.getNum()+" " + dic.getName()+"'";
			}
		}
		s += "];";
		content.append(s);
		
		s = "var basePrdtype=[''";
		for(Dic dic : dicList){
			if(dic.getCode().equals(Dic.PRD_TYPE)){
				s += ",'" + dic.getNum()+" " + dic.getName()+"'";
			}
		}
		s += "];";
		content.append(s);
		loginUser.setStaticValue(content.toString());
	}
}
