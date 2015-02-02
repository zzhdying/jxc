package com.mglf.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.filter.DelegatingFilterProxy;

import sun.invoke.empty.Empty;

import com.mglf.dto.LoginUserDetails;
import com.mglf.dto.UserDetails;
import com.mglf.entity.User;
import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.CookieUtil;
import com.mglf.util.EmptyUtil;
import com.mglf.util.SpringSecurityUtils;


/**
 * access过滤器
 * @author zhongzhuohan
 *
 */
public class AccessFilter extends DelegatingFilterProxy {

	private Class loginUserDetailsCls;
		
	public boolean isInit = false;
	
	/**
	 * 专场域名
	 */
	public static final List<String> staticFile = new ArrayList<String>(Arrays.asList(
			".css",".js",
			".jpg",".jpeg",".png",".gif",".icon",
			".otf",".eot",".svg",".ttf",".woff"));
	/**
	 * 专场域名
	 */
	public static final List<String> urlFilter = new ArrayList<String>(Arrays.asList(
			"/login"));
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		if(!isInit){
			servletRequest.getServletContext().setAttribute("rootUrl", ConfigUtil.readSysValue("rootUrl"));
			isInit = true;
		}
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session = request.getSession();
		
		SecurityContext securityContext = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		 		
		Authentication auth = null;
		
		if(securityContext != null){
			auth = securityContext.getAuthentication();
		}else{
			securityContext = SecurityContextHolder.createEmptyContext();
			SecurityContextHolder.setContext(securityContext);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
		}
		
		LoginUserDetails userDetials = null;
		
		if(auth != null){
			if(auth.getPrincipal() instanceof LoginUserDetails){
				userDetials = (LoginUserDetails)auth.getPrincipal();
			}
		}
		
		String authKey = CookieUtil.readCookie(request, "auth");
		String defaultPage = CookieUtil.readCookie(request, "defaultPage");
		
		if(userDetials == null && !request.getRequestURI().endsWith("/login") ){		
			if(authKey != null && !"".equals(authKey)){
				User user = (User)CacheUtil.load(CacheUtil.GROUP_AUTH, authKey);
				if(user != null){
					if(loginUserDetailsCls == null){
						try{
							loginUserDetailsCls = Class.forName("com.mglf.dto.UserDetails");
						}catch(Exception e){
						}
					}
										
					try{
						userDetials = (LoginUserDetails)loginUserDetailsCls.getDeclaredConstructor(User.class).newInstance(user);
					}catch(Exception e){
					}
					
					PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetials,
							userDetials.getPassword(), userDetials.getAuthorities());

					authentication.setDetails(new WebAuthenticationDetails(request));
					
					securityContext.setAuthentication(authentication);
					
					if(defaultPage != null && !"".equals(defaultPage) && !request.getRequestURI().equals(defaultPage)){
						
						Cookie cookie = new Cookie("defaultPage", "");
						cookie.setPath("/");
						response.addCookie(cookie);
						
						response.sendRedirect(defaultPage);
						return;
					}
				}
			}else{
				DefaultSavedRequest savedRequest = (DefaultSavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
				
				if(savedRequest != null){
					Cookie cookie = new Cookie("defaultPage", savedRequest.getRedirectUrl());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		if(EmptyUtil.isEmpty(userDetials) && !isUrlContain(request.getRequestURI(),urlFilter)){
			boolean f = false;
			for(String filter : staticFile){
				if(request.getRequestURI().endsWith(filter)){
					f = true;
					break;
				}
			}
			if(!f){
				//response.sendRedirect(ConfigUtil.readSysValue("rootUrl")+"/login/index");
				//return ;
			}
		}
		if(!EmptyUtil.isEmpty(userDetials)){
			if(defaultPage != null && !"".equals(defaultPage) && !request.getRequestURI().equals(defaultPage)){
				
				Cookie cookie = new Cookie("defaultPage", "");
				cookie.setPath("/");
				response.addCookie(cookie);
				
				response.sendRedirect(defaultPage);
				return;
			}
		}
		super.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	private static boolean isUrlContain(String main , List<String> value){
		for(String filter : value){
			if(main.indexOf(filter)>=0){
				return true;
			}
		}
		return false;
	}
}
