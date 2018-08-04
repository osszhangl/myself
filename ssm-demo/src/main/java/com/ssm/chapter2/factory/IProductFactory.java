package com.ssm.chapter2.factory;
/**
 * 
 * @ClassName IProductFactory
 * @Description 这里只是伪代码。不是真正的实现代码，用于定义工厂规范
 * @Author Administrator
 * @Date 2018年7月29日 下午1:04:25
 */
public interface IProductFactory {
	
	public IProduct createProduct(String productNo);
	
	
}
