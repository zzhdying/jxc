package com.mglf.biz.controller.prd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/prd")
public class PrdController {
	
	@RequestMapping("/")
	public ModelAndView idx(String address) throws Exception {
		ModelMap map = new ModelMap();
		
		return new ModelAndView("web/prd.jsp", map);
	}
}
