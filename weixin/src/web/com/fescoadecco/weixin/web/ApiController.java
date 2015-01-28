package com.fescoadecco.weixin.web;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mglf.base.BaseController;

/**
 * 微信api controller
 * 
 * @author xiapengcheng
 * 
 */
@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {
	static Logger logger = Logger.getLogger(ApiController.class);

	/**
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public String menu(){
		return null;
	}
}
