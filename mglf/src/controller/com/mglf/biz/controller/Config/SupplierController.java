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
import com.mglf.entity.Supplier;
import com.mglf.service.DicService;
import com.mglf.service.PrdService;
import com.mglf.service.StaticValueService;
import com.mglf.service.SupplierService;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private StaticValueService staticValueService;
	
	@RequestMapping("/index")
	public ModelAndView idx() throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		ModelMap map = new ModelMap();
		List<Supplier> list = supplierService.getSupplierByEntid(loginUser.getEntid());
		map.put("list", list);
		return new ModelAndView("web/supplier/index.jsp", map);
	}
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelMap map = new ModelMap();
		return new ModelAndView("web/supplier/add.jsp", map);
	}
	
	@RequestMapping("/adddo")
	public ModelAndView adddo(Supplier supplier) throws Exception {
		ModelMap map = new ModelMap();
		supplierService.add(supplier);
		staticValueService.initStaticValueString();
		return new ModelAndView("redirect:/supplier/index", map);
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String id) throws Exception {
		ModelMap map = new ModelMap();
		if(EmptyUtil.isEmpty(id)){
			throw new AppException();
		}
		Supplier supplier = supplierService.getSupplierByid(id);
		map.addAttribute("dic", supplier);
		return new ModelAndView("web/supplier/edit.jsp", map);
	}
	
	@RequestMapping("/editdo")
	public ModelAndView editdo(Supplier supplier) throws Exception {
		UserDetails loginUser = (UserDetails)SpringSecurityUtils.getLoginUser();
		Supplier dbSupp = supplierService.getSupplierByid(supplier.getId());
		if(EmptyUtil.isEmpty(dbSupp) || !dbSupp.getEntid().equals(loginUser.getEntid())){
			throw new AppException();
		}
		supplier.setEntid(loginUser.getEntid());
		supplierService.update(supplier);
		staticValueService.initStaticValueString();
		return new ModelAndView("redirect:/supplier/index");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public JsonResult<String> del(String id) throws Exception {
		supplierService.remove(id);
		staticValueService.initStaticValueString();
		return new JsonResult<String>();
	}
}
