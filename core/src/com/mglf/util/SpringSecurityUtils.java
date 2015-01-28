package com.mglf.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.mglf.dto.LoginUserDetails;

/**
 * 获取用户登录的相关信息工具类
 * 
 * @author zhongzhuohan
 * 
 */
public class SpringSecurityUtils {

	/**
	 * 获取登录用户信息
	 * 
	 * @return LoginUserDetails或其子类
	 */
	public static LoginUserDetails getLoginUser() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if ((principal instanceof LoginUserDetails)) {
				return (LoginUserDetails) principal;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends LoginUserDetails> T getCurrentUser() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if ((principal instanceof LoginUserDetails)) {
				return (T) principal;
			}
		}
		return null;
	}

	public static String getCurrentUserName() {
		Authentication authentication = getAuthentication();
		if ((authentication != null) && (authentication.getPrincipal() != null)) {
			return authentication.getName();
		}
		return "";
	}

	public static String getCurrentUserIp() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object details = authentication.getDetails();
			if ((details instanceof WebAuthenticationDetails)) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				return webDetails.getRemoteAddress();
			}
		}
		return "";
	}

	public static void saveUserDetailsToContext(UserDetails userDetails, HttpServletRequest request) {
		PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static void clearUserDetailsToContext() {
		SecurityContextHolder.clearContext();
	}

	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			return context.getAuthentication();
		}
		return null;
	}

}
