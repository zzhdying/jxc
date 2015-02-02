package com.mglf.biz.controller.prd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.dao.PrdMapper;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Prd;
import com.mglf.service.PrdService;
import com.mglf.util.SpringSecurityUtils;

@Controller
@RequestMapping("/prd")
public class PrdController {
	
	@Autowired
	private PrdService prdService;
	
	@RequestMapping("/index")
	public ModelAndView idx(String address) throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		ModelMap map = new ModelMap();
		List<Prd> prdList = prdService.getEntAllPrds(loginUser.getEntid());
		map.put("prdlist", prdList);
		return new ModelAndView("web/prd/prd.jsp", map);
	}
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		ModelMap map = new ModelMap();
		return new ModelAndView("web/prd/add.jsp", map);
	}
}
