package com.mglf.interceptor.token;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mglf.base.AppException;
/**
 * token 拦截器  防止表单重复提交
 * 
 * @author zhongzhuohan
 *
 */
public class SessionTokenInterceptor extends HandlerInterceptorAdapter{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}
 
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Token clsToken = handlerMethod.getBean().getClass().getSuperclass().getAnnotation(Token.class);
		
		if(clsToken != null){
			Method method = handlerMethod.getMethod();
			Token methodToken = method.getAnnotation(Token.class);

			if(methodToken != null){
				if(!methodToken.ignore()){
					String token = (String)request.getSession().getAttribute("token");
					if(token != null){
						throw new AppException("重复提交！");
					}

					token = String.valueOf(System.currentTimeMillis());
					request.getSession().setAttribute("token", token);	
				}
			}else if(methodToken == null){
				request.getSession().removeAttribute("token");
			}
			
		}

		return super.preHandle(request, response, handler);
     }
 
}
