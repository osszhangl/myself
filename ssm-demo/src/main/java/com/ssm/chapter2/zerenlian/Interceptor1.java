package com.ssm.chapter2.zerenlian;
import java.lang.reflect.Method;

import com.ssm.chapter2.interceptor.Interceptor;

public class Interceptor1 implements Interceptor {

	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("[拦截器1]的before方法");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		
		
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("[拦截器1]的after方法");
		
	}

}
