package com.ssm.chapter2.factory;
/**
 * 
 * @ClassName IProduct
 * @Description 这里只是伪代码，不是真正的实现代码，
 * @Author Administrator
 * @Date 2018年7月29日 下午1:10:30
 */
public class IProduct {
	
	 private String product;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public IProduct(String product) {
		super();
		this.product = product;
	}
	
	 
	 
}
