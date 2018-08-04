package com.ssm.chapter2.proxy;

import java.lang.reflect.Method;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName CglibProxyDemo
 * @Description 使用CGLIB实现动态代理,他与JDK动态代理最大的区别（就是他不需要被代理的类实现接口）
 * @Author Administrator
 * @Date 2018年7月27日 下午2:40:31
 */
public class CglibProxyDemo implements MethodInterceptor {
	
	/**
	 * 
	 * @Description 通过Class对象来生成代理对象
	 * @param cls 类对象（注意不是实例对象，Class对象和实例对象是不同的概念可以上网查询一下）
	 * @return 代理对象
	 */
	public Object getProxy(Class cls){
		//创建CGLIB enhancer 增强类对象
		Enhancer enhancer = new Enhancer();
		//设置增强类型
		enhancer.setSuperclass(cls);
		//定义代理逻辑对象为当前对象，当前对象必须实现MethodInterceptor接口
		enhancer.setCallback(this);
		//生成并返回代理对象
		return enhancer.create();
		
	}

	/**
	 * 
	 * @Description 代理逻辑方法
	 * @param proxy 代理对象
	 * @param method 方法
	 * @param args 方法参数
	 * @param methodProxy 方法代理
	 * @return 代理逻辑返回
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

		System.out.println("调用真实对象之前");
		//CGLIB反射调用真实对象方法
		Object result = methodProxy.invokeSuper(proxy, args);
		
		System.out.println("调用真实对象后");
		
		return result;
	}
	
	@Test
	public void Test(){
		CglibProxyDemo cpd = new CglibProxyDemo();
		
		HelloWorldImpl proxy = (HelloWorldImpl)cpd.getProxy(HelloWorldImpl.class);
		
		proxy.sayHello();
		
	}
	
	
	
	
	

}
