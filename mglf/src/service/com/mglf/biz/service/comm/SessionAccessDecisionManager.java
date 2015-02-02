package com.mglf.biz.service.comm;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.dto.UserDetails;

/**
 * 权限控制service
 * 实现spring security AccessDecisionManager接口
 * @author zhuben
 *
 */
@Component
@Transactional
public class SessionAccessDecisionManager implements AccessDecisionManager {

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {

		ConfigAttribute ca = (ConfigAttribute)configAttributes.iterator().next();
		if(ca != null && "IS_AUTHENTICATED_ANONYMOUSLY".equals(ca.toString())){
			return;
		}
		
		if(authentication instanceof AnonymousAuthenticationToken){
			throw new AccessDeniedException("");
		}

		//access check ......
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		FilterInvocation filterInvocation = (FilterInvocation)object;
//		String reqUrl = filterInvocation.getRequestUrl();
		
		
//		System.out.println("url:"+reqUrl);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(org.springframework.security.access.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
