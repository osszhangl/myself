package com.ssm.chapter2.observers;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

/**
 * 
 * @ClassName JingDongObserver
 * @Description 京东电商接口（京东观察者）
 * @Author Administrator
 * @Date 2018年7月29日 上午10:06:22
 */
public class JingDongObserver implements Observer {

	@Override
	public void update(Observable o, Object product) {
		String newProduct = (String)product;
		System.out.println("发送新产品【"+newProduct+"】 同步到京东商城");
	}

	@Test
	public void test3(){
		System.out.println("ll");
		
	}
	
	
	
	
}
