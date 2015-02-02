package com.mglf.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户登陆信息对象
 * 
 * @author zhongzhuohan
 * 
 */
@SuppressWarnings("serial")
public abstract class LoginUserDetails implements UserDetails {

	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority ga = new GrantedAuthority() {
			public String getAuthority() {
				return "ROLE";
			}
		};

		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(ga);
		return list;
	}

	public abstract String getUserId();

	public abstract String getRealName();

	public abstract String getUsername();

	public abstract String getPassword();
	
	public abstract String getEntid();

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
