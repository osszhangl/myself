package com.ssm.chapter2.factory;
/**
 * 
 * @ClassName ProductFactory
 * @Description 这边只是伪代码，不是真正的实现代码 ,公共的工厂
 * @Author Administrator
 * @Date 2018年7月29日 下午1:00:58
 */
public class ProductFactory implements IProductFactory {

	@Override
	public IProduct createProduct(String productNo) {

		char ch = productNo.charAt(0);
		IProductFactory factory = null;
		if(ch=='1'){
			factory = new ProductFactory1();
		}else if(ch == '2'){
			factory = new ProductFactory2();
		}else if(ch == '3'){
			factory = new ProductFactory3();
		}
		if(factory !=null){
			return factory.createProduct(productNo);
		}
		
		return null;
	}
	
	
	
	
	
	
}
