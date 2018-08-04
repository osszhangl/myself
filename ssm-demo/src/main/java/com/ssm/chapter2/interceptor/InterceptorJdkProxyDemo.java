package com.ssm.chapter2.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.ssm.chapter2.proxy.HelloWorld;
import com.ssm.chapter2.proxy.HelloWorldImpl;

/**
 * 
 * @ClassName InterceptorJdkProxyDemo
 * @Description JDK动态代理中使用拦截器
 * @Author Administrator
 * @Date 2018年7月27日 下午5:39:16
 */
public class InterceptorJdkProxyDemo implements InvocationHandler {

	private Object target;// 真实对象
	private String interceptorClass = null;// 拦截器全限定名

	public InterceptorJdkProxyDemo(Object target, String interceptorClass) {
		this.target = target;
		this.interceptorClass = interceptorClass;
	}

	/**
	 * 
	 * @Description 绑定代理对象，并返回一个代理对象
	 * @param target
	 *            真实对象
	 * @param interceptorClass
	 *            拦截器全限定名
	 * @return 代理对象
	 */
	public static Object bind(Object target, String interceptorClass) {
		// 获取代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InterceptorJdkProxyDemo(target, interceptorClass));

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (interceptorClass == null) {
			// 没有设置拦截器则直接返回原有方法
			return method.invoke(target, args);
		}
		Object result = null;
		// 通过反射生成拦截器
		Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
		// 调用前置方法
		if (interceptor.before(proxy, target, method, args)) {
			// 反射原有对象方法
			result = method.invoke(target, args);

		} else {// 放回false执行around方法

			interceptor.around(proxy, target, method, args);

		}
		// 调用后置方法
		interceptor.after(proxy, target, method, args);
		return result;
		
	}

	public static void main(String[] args) {
		HelloWorld proxy = (HelloWorld) InterceptorJdkProxyDemo.bind(new HelloWorldImpl(),
				"com.ssm.chapter2.interceptor.InterceptorImpl");
		proxy.sayHello();
		
	}
	
}
