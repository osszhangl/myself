package com.ssm.chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;


/**
 * 使用JDK动态代理必须实现java.lang.reflect.InvocationHandler接口，它里面定义了一个invoke方法，并提供
 * 接口数组用于下挂代理对象。实现动态代理分为两个步骤：1，在代理对象和真实对象之间建立代理关系；2，实现代理对象的代理逻辑方法。
 * @author Administrator
 *
 */
public class JdkProxyDemo implements InvocationHandler {

	private Object target = null;//真实对象
	/**
	 * 
	 * @Description 该方法用于建立代理对象和真实对象的代理关系，并返回代理对象
	 * @param target 真实对象
	 * @return 代理对象
	 */
	public Object bind(Object target){
		this.target = target;
		//用反射获取代理对象，参数列表（真实对象对应的类加载器；将生成的代理对象下挂在那个接口下面这里是target实现的接口；实现方法逻辑的代理类this是当前对象他实现了InvocationHandler接口的invoke方法，他是代理逻辑方法的实现方法）
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	/**
	 * 
	 * @Description 代理方法逻辑
	 * @param proxy 代理对象
	 * @param method 当前调度的方法
	 * @param args 调度方法的参数
	 * @return 调度结果返回
	 * @throws Throwable 
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("在调度真实对象前的服务");
		Object obj = method.invoke(target, args);
		System.out.println("在调度真实对象之后的服务");
		return obj;
		
	}
	
	/**
	 * 
	 * @Description 这里有用到junit的测试，小伙伴自己导一下包就可以了，我就不赘述了。
	 */
	@Test
	public void test(){
		JdkProxyDemo jdk = new JdkProxyDemo();
		HelloWorld proxy =(HelloWorld)jdk.bind(new HelloWorldImpl());
		proxy.sayHello();
		
		
		
		
	}
	
}
