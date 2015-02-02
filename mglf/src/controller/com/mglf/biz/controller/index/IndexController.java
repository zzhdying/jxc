package com.mglf.biz.controller.index;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.biz.service.web.WPerUserService;
import com.mglf.dto.LoginUserDetails;
import com.mglf.mvc.NonHtmlEscape;
import com.mglf.util.SpringSecurityUtils;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("/")
	public ModelAndView idx(String address) throws Exception {
		ModelMap map = new ModelMap();
		LoginUserDetails loginUser = SpringSecurityUtils.getLoginUser();
		return new ModelAndView("web/index.jsp", map);
	}
	
	@RequestMapping("/index")
	public ModelAndView index(String address) throws Exception {		
		return new ModelAndView("redirect:/");
	}
}
