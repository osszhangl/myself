package com.ssm.chapter2.observers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.junit.Test;

/**
 * 
 * @ClassName ProductList
 * @Description 被观察的产品列表（被观察者）
 * @Author Administrator
 * @Date 2018年7月28日 下午5:47:41
 */
public class ProductList extends Observable{
	
	private List<String> productList = null;//产品列表
	private static ProductList instance;//类唯一实例
	private ProductList(){}//构造方法私有化
	/**
	 * 
	 * @Description 采用单例模式取得唯一实例（被观察对象）
	 * @return
	 */
	public static ProductList getInstance(){
		if(instance == null){
			instance = new ProductList();
			instance.productList = new ArrayList<String>();
		}
		return instance;
	}
	
	/**
	 * 
	 * @Description 增加观察者（电商接口） 
	 * @param observer 观察者
	 */
	public void addProductListObserver(Observer observer){
		this.addProductListObserver(observer);
		
	}
	/**
	 * 
	 * @Description 新增产品 
	 * @param newProduct 新产品
	 */
	public void addProduct(String newProduct){
		productList.add(newProduct);
		System.out.println("产品列表新增了产品"+newProduct);
		this.setChanged();//继承Observable所带来的方法用于告知观察者被观察对象发生变化
		this.notifyObservers(newProduct);//继承Observable所带来的方法通知观察者发生相应的动作，并传递新产品
		
	}
	
	public static void main(String[] args) {
		ProductList observable = ProductList.getInstance();
		TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
		JingDongObserver jingDongObserver = new JingDongObserver();
		observable.addObserver(taoBaoObserver);
		observable.addObserver(jingDongObserver);
		observable.addProduct("新增产品1");
		
		
	}
	
}
