package com.mglf.interceptor.token;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * token注解
 * @author zhongzhuohan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	 boolean save() default false;
	 
	 boolean remove() default false;
	 
	 boolean ignore() default false;
}
