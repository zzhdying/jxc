package com.mglf.biz.controller.staticConfig;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.base.AppException;
import com.mglf.dao.PrdMapper;
import com.mglf.dto.JsonResult;
import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.Dic;
import com.mglf.entity.Prd;
import com.mglf.service.DicService;
import com.mglf.service.PrdService;
import com.mglf.service.StaticValueService;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

@Controller
@RequestMapping("/mglf")
public class StaticController {
	
	@Autowired
	private StaticValueService staticValueService;
	
	@RequestMapping("/js")
	public void idx(HttpServletResponse response) throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		if(EmptyUtil.isEmpty(loginUser.getStaticValue())){
			staticValueService.initStaticValueString();
		}
		response.getWriter().print(loginUser.getStaticValue());
	}
	
}
