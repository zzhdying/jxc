package com.mglf.dto;

import com.mglf.entity.User;
import com.mglf.util.ConfigUtil;




/**
 * per用户登陆信息DTO
 * @author zhuben
 */
public class UserDetails  extends LoginUserDetails  {

	/**
	 * per用户实体对象
	 */
	private User user;
	

	public UserDetails(User user) {
		this.user = user;
	}
	
	public UserDetails() {
	}

	/**
	 * 获取per用户实体对象
	 * @return per用户实体对象
	 */
	public User getUser() {
		return user;
	}


	/**
	 * 设置per用户实体对象
	 * @param perUser per用户实体对象
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/* (non-Javadoc)
	 * @see com.fescoadecco.dto.LoginUserDetails#getPassword()
	 */
	public String getPassword() {
		if (user!=null) {
			return user.getPassword();
		}else{
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.fescoadecco.dto.LoginUserDetails#getUsername()
	 */
	public String getUsername() {
		if (user!=null) {
			return user.getUsername();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.fescoadecco.dto.LoginUserDetails#getUserId()
	 */
	public String getUserId(){
		if (user!=null) {
			return user.getId();
		}else{
			return null;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.fescoadecco.dto.LoginUserDetails#getRealName()
	 */
	public String getRealName(){
		if (user!=null) {
			return user.getName();
		}else{
			return null;
		}
		
	}
	
	public String getEntid(){
		if (user!=null) {
			return user.getEntid();
		}else{
			return null;
		}
	}
	
	public boolean isActive(){
		return true;
	}
	
	//密码生成格式
	public String getSalt() {
		return user.getId() + ConfigUtil.readSysValue("pwdRandom");
	}
	

}
