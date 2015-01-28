package com.mglf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.JasperException;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.mglf.base.BaseController;
import com.mglf.base.NotFoundException;
import com.mglf.dto.JsonResult;
import com.mglf.interceptor.LogInterceptor;

public class FaDispatcherServlet extends DispatcherServlet {

	private static final ThreadLocal<Integer> doServiceCount = new ThreadLocal<Integer>();
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getRequestURI().indexOf("/login")>0){
				response.addHeader("login", "true");
			}
			
			Integer count = doServiceCount.get();
			if(count == null){
				count = 1;
			}else if(count > 2){
				throw new Exception("doServiceCount > 2");
			}else{
				count = count + 1;
			}
			doServiceCount.set(count);
			
			String ip = BaseController.getIpAddr(request);
			LogInterceptor.webIp.set(ip);
			
			logger.debug("doService:" + request.getRequestURI());
			super.doService(request, response);
			
			
			count = doServiceCount.get();
			if(count != null){
				count = count -1;
				doServiceCount.set(count);
			}
		} catch (JasperException e) {
			doServiceCount.set(null);
			
			logger.error("doService:", e);
			try {
				request.setAttribute("ex", e);
				request.getRequestDispatcher("/WEB-INF/jsp/comm/error.jsp").forward(request, response);
			} catch (Exception ee) {
				logger.error("doService:", ee);
			}
		} catch (NotFoundException nfe){
			doServiceCount.set(null);
			
			logger.error("doService:", nfe);
			try {
				request.setAttribute("ex", null);
				request.getRequestDispatcher("/WEB-INF/jsp/comm/error.jsp").forward(request, response);
			} catch (Exception ee) {
				logger.error("doService:", ee);
			}
		} catch (Exception e) {
			doServiceCount.set(null);
			
			logger.error("doService:", e);
			try {
				Class webRetType = LogInterceptor.getWebRetType();
				
				if(webRetType == ModelAndView.class){
					request.setAttribute("ex", e);
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}else{
					response.setContentType("application/json");
					
					JSONObject json = new JSONObject();
					json.put("status", JsonResult.FAIL);
					json.put("msg", e.toString());

					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.close();	
				}
				
			} catch (Exception ee) {
				logger.error("doService:", ee);
			}
		}
	}
}
