package com.ssm.chapter2.zerenlian;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import com.ssm.chapter2.interceptor.Interceptor;
import com.ssm.chapter2.proxy.HelloWorld;
import com.ssm.chapter2.proxy.HelloWorldImpl;


/**
 * 
 * @ClassName InterceptorJdkProxyDemo
 * @Description JDK动态代理中使用拦截器（这个类和interceptor保中的是同个类，复制过来只是为了比较好测试多个拦截器）
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

		} else {// 返回false执行around方法

			interceptor.around(proxy, target, method, args);

		}
		// 调用后置方法
		interceptor.after(proxy, target, method, args);
		return result;
	}

	public static void main(String[] args) {
		HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxyDemo.bind(new HelloWorldImpl(),
				"com.ssm.chapter2.zerenlian.Interceptor1");
		HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxyDemo.bind(proxy1,
				"com.ssm.chapter2.zerenlian.Interceptor2");
		HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxyDemo.bind(proxy2,
				"com.ssm.chapter2.zerenlian.Interceptor3");
		proxy3.sayHello();
	}

}
