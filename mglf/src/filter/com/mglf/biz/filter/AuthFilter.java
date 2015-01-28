package com.mglf.biz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;


/**
 * Mobile端的过滤器
 * @author jinxiaochen
 *
 */
public class AuthFilter implements Filter {
	
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	//public final static String[] needRmd = new String[]{"mobile/jobs/", "mobile/company/", "mobile/appdetail/", "mobile/refdetail/"};

	public final static String[] needRmd = new String[]{"mobile/index",
		"mobile/jobs/", 
		"mobile/company/", 
		"mobile/appdetail/" , 
		"mobile/personaljd" , 
		"mobile/authority/per/chgtohr"};
	
	public final static String loginUrl = "mobile/login";
	
	public final static String indexUrl = "/mobile/index";
	
	public final static String entUrl = ConfigUtil.readSysValue("blueWebUrl") + ConfigUtil.readSysValue("BlueEnterpriseWeb");
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String currentURL = request.getRequestURI();
		request.setAttribute("entUrl", entUrl);
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
//		perUserService = (PerUserService)wac.getBean("perUserService");
	}

}
