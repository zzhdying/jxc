package com.mglf.biz.controller.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.biz.service.web.WPerUserService;
import com.mglf.dto.LoginUserDetails;
import com.mglf.entity.User;
import com.mglf.mvc.NonHtmlEscape;
import com.mglf.service.UserService;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView login(){
		return new ModelAndView("redirect:/login/index");
	}
	@RequestMapping("/index")
	public ModelAndView loginindex(){
		ModelMap map = new ModelMap();
		return new ModelAndView("web/login.jsp", map);
	}
	
	@RequestMapping("do")
	public ModelAndView dologin(String username , String pass , HttpServletRequest request){
		ModelMap map = new ModelMap();
		User user = userService.doLogin(username, pass, request);
		
		if(EmptyUtil.isEmpty(user)){
			return new ModelAndView("web/login.jsp",map);
		}
		return new ModelAndView("redirect:/",map);
	}
	
	@RequestMapping("out")
	public ModelAndView dologout(){
		SpringSecurityUtils.clearUserDetailsToContext();
		return new ModelAndView("redirect:/login/index");
	}
}
