<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mglf.util.EmptyUtil"%>
<%@ page import="com.mglf.util.ConfigUtil" %>
<%@ page import="com.mglf.constant.ProjectStaticValue" %>
<%@page import="com.mglf.dto.UserDetails" %>
<%@page import="com.mglf.util.SpringSecurityUtils" %>
<%
request.setAttribute("adminName", ProjectStaticValue.ADMIN_NAME);
request.setAttribute("adminTel", ProjectStaticValue.ADMIN_TEL);
request.setAttribute("adminQQ", ProjectStaticValue.ADMIN_QQ);
request.setAttribute("adminEmail", ProjectStaticValue.ADMIN_EMAIL);
%>
<%
request.setAttribute("root", ConfigUtil.readSysValue("rootUrl"));
%>
<%
UserDetails userDetials = (UserDetails) SpringSecurityUtils.getLoginUser();
if(!EmptyUtil.isEmpty(userDetials)){
	request.setAttribute("userinfo", userDetials.getUser());	
}
%>