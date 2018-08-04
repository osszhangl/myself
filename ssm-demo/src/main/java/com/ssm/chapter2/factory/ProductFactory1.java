package com.ssm.chapter2.factory;
/**
 * 
 * @ClassName ProductFactory
 * @Description 这边只是伪代码，不是真正的实现代码,实例工厂
 * @Author Administrator
 * @Date 2018年7月29日 下午1:00:58
 */
public class ProductFactory1 implements IProductFactory {

	@Override
	public IProduct createProduct(String productNo) {
		IProduct product = new IProduct(productNo);
		return product;
	}
	
}
