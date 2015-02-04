package com.mglf.biz.controller.Order;

import java.util.List;

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
import com.mglf.entity.Prd;
import com.mglf.service.PrdService;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

@Controller
@RequestMapping("/order")
public class OrderController {
	
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
		ModelMap map = new ModelMap();
		return new ModelAndView("web/prd/add.jsp", map);
	}
	
	@RequestMapping("/adddo")
	public ModelAndView adddo(Prd prd) throws Exception {
		ModelMap map = new ModelMap();
		prdService.add(prd);
		return new ModelAndView("redirect:/prd/index", map);
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String id) throws Exception {
		ModelMap map = new ModelMap();
		if(EmptyUtil.isEmpty(id)){
			throw new AppException();
		}
		Prd prd = prdService.getPrdByid(id);
		map.addAttribute("prd", prd);
		return new ModelAndView("web/prd/edit.jsp", map);
	}
	
	@RequestMapping("/editdo")
	public ModelAndView editdo(Prd prd) throws Exception {
		prdService.update(prd);
		return new ModelAndView("redirect:/prd/index");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public JsonResult<String> del(String id) throws Exception {
		prdService.removePrd(id);
		return new JsonResult<String>();
	}
}
