package com.mglf.biz.controller.Config;

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
import com.mglf.entity.Dic;
import com.mglf.entity.Prd;
import com.mglf.service.DicService;
import com.mglf.service.PrdService;
import com.mglf.service.StaticValueService;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

@Controller
@RequestMapping("/unit")
public class UnitController {
	
	@Autowired
	private DicService dicService;
	@Autowired
	private StaticValueService staticValueService;
	
	@RequestMapping("/index")
	public ModelAndView idx() throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		ModelMap map = new ModelMap();
		List<Dic> dicList = dicService.getDicByCode(Dic.PRD_UNIT, loginUser.getEntid());
		map.put("unitlist", dicList);
		return new ModelAndView("web/unit/index.jsp", map);
	}
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelMap map = new ModelMap();
		return new ModelAndView("web/unit/add.jsp", map);
	}
	
	@RequestMapping("/adddo")
	public ModelAndView adddo(Dic dic) throws Exception {
		ModelMap map = new ModelMap();
		dic.setCode(Dic.PRD_UNIT);
		dicService.add(dic);
		staticValueService.initStaticValueString();
		return new ModelAndView("redirect:/unit/index", map);
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String id) throws Exception {
		ModelMap map = new ModelMap();
		if(EmptyUtil.isEmpty(id)){
			throw new AppException();
		}
		Dic dic = dicService.getDicById(id);
		map.addAttribute("unit", dic);
		return new ModelAndView("web/unit/edit.jsp", map);
	}
	
	@RequestMapping("/editdo")
	public ModelAndView editdo(Dic dic) throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		Dic dbDic = dicService.getDicById(dic.getId());
		if(EmptyUtil.isEmpty(dbDic) || !dbDic.getEntid().equals(loginUser.getEntid())){
			throw new AppException();
		}
		dic.setEntid(loginUser.getEntid());
		dic.setCode(Dic.PRD_UNIT);
		dicService.update(dic);
		staticValueService.initStaticValueString();
		return new ModelAndView("redirect:/unit/index");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public JsonResult<String> del(String id) throws Exception {
		dicService.remove(id);
		staticValueService.initStaticValueString();
		return new JsonResult<String>();
	}
	
}
