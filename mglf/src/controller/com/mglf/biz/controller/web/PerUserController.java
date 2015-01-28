package com.mglf.biz.controller.web;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.biz.service.web.WPerUserService;
import com.mglf.mvc.NonHtmlEscape;

@Controller
@RequestMapping("/")
public class PerUserController {
	
	@Autowired
	public WPerUserService wPerUserService;
	
	@RequestMapping("/index")
	public ModelAndView initSearch(String address) throws Exception {
		ModelMap map = new ModelMap();
		
		return new ModelAndView("mobile/index.jsp", map);
	}
}
