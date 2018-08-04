package com.ssm.chapter2.interceptor;

import java.lang.reflect.Method;
/**
 * 
 * @ClassName Interceptor
 * @Description 拦截器接口
 * @Author Administrator
 * @Date 2018年7月27日 下午5:29:14
 */
public interface Interceptor {
	/**
	 * 
	 * @Description 
	 * @param proxy 代理对象
	 * @param target 真实对象
	 * @param method 方法
	 * @param args 运行方法参数
	 * @return 返回为true时，则反射真实对象的方法，false时，则调用around方法
	 */
	public boolean before( Object proxy,Object target,Method method,Object[] args);
	
	public void around( Object proxy,Object target,Method method,Object[] args);
	
	public void after( Object proxy,Object target,Method method,Object[] args);
	
	
}







