package com.mglf.interceptor.token;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mglf.interceptor.token.Token;

import com.mglf.interceptor.token.Token;
/**
 * token 拦截器  防止表单重复提交
 * 
 * @author zhongzhuohan
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
            	//进入input页面时annotation.save()=true，方法前加注解实现
                boolean needSaveSession = annotation.save();
                
                if (needSaveSession) {
                	//设置token值
                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
                }
                
                //进行保存操作时annotation.remove()=true，方法前加注解实现
                boolean needRemoveSession = annotation.remove();
                
                //needRemoveSession 为true时，删除session中的token
                if (needRemoveSession) {
                	//判断request的session中的token值是否与客户端中的token值相等
                	//不相等return false 请求结束
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    //相等移除session中的token
                    request.getSession(false).removeAttribute("token");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }
 
	/**
	 * 判断session中的token值是否与客户端中的token值相等
	 * 
	 * @param request
	 * 
	 * @return boolean
	 */
    private boolean isRepeatSubmit(HttpServletRequest request) {
    	//获得session中的token
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        //客户端传上来的token
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        //判断session中的token是否与客户端传上来的token相等
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
