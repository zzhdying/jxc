package com.mglf.biz.controller.mobile;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.mvc.NonHtmlEscape;

@Controller
@RequestMapping("/mobile")
public class BaiduMapController {
	
	
	
	@RequestMapping("/initSearch")
	public ModelAndView initSearch(String address) throws Exception {
		ModelMap map = new ModelMap();
		address = new String(address.getBytes("iso8859-1"), "UTF-8");
		map.addAttribute("address", address);
		return new ModelAndView("mobile/baidumap.jsp", map);
	}
	
	
	
	@RequestMapping("/test")
	@NonHtmlEscape
	public ModelAndView test(String code, String state) throws Exception {

//		resumesBiz.importReumeFromLinkedin("aef278da-7b27-4c04-bb16-123c37d2a601",
//				ImportResumeFromLinkedinUtils.FROM_TYPE_MOBILE, code, state);
//		ModelMap map = new ModelMap();
//		map.addAttribute("msg", "您浏览的职位不存在");
		return new ModelAndView("mobile/index.jsp");
	}
}
