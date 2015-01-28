package com.mglf.servlet;

import java.io.IOException;

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

import com.mglf.dto.LoginUserDetails;
import com.mglf.entity.User;
import com.mglf.util.CacheUtil;
import com.mglf.util.ConfigUtil;


/**
 * access过滤器
 * @author zhongzhuohan
 *
 */
public class AccessFilter extends DelegatingFilterProxy {

	private Class loginUserDetailsCls;
	
	
	public final static String entUrl = ConfigUtil.readSysValue("blueWebUrl") + ConfigUtil.readSysValue("BlueEnterpriseWeb");
	
	public final static String perUrl = ConfigUtil.readSysValue("blueWebUrl") + ConfigUtil.readSysValue("BluePersonWeb");
	
	
	public boolean isInit = false;
	
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		if(!isInit){
			servletRequest.getServletContext().setAttribute("entUrl", entUrl);
			servletRequest.getServletContext().setAttribute("perUrl", perUrl);
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
		
		if(userDetials == null && !request.getRequestURI().endsWith("/login") ){
			Cookie[] cs = request.getCookies();
			String authKey = null;
			String defaultPage = null;
			
			if(cs != null){			
				for(Cookie c : cs){
					if("auth".equals(c.getName())){
						authKey = c.getValue();
					}else if("defaultPage".equals(c.getName())){
						defaultPage = c.getValue();
					}
				}
			}	
			
			if(authKey != null && !"".equals(authKey)){
				User user = (User)CacheUtil.load(CacheUtil.GROUP_AUTH, authKey);
				if(user != null){
					if(loginUserDetailsCls == null){
						try{
							loginUserDetailsCls = Class.forName("com.mglf.per.dto.PerUserDetails");
						}catch(Exception e){
						}
					}
					if(loginUserDetailsCls == null){
						try{
							loginUserDetailsCls = Class.forName("com.mglf.ent.dto.user.EntUserDetails");
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
				
		super.doFilter(servletRequest, servletResponse, filterChain);
	}

	

}
