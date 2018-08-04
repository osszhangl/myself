package com.ssm.chapter3.sqlsessionfactory;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * 
 * @ClassName ProductXmlSqlSessionFactory
 * @Description 通过XML配置文件生成SqlSessionFactory对象，我这边到的Mybatis是3.2.8版本的
 * @Author Administrator
 * @Date 2018年8月1日 下午4:49:41
 */
public class XmlProductSqlSessionFactory  {
	
	private final static Class<XmlProductSqlSessionFactory> LOCK = 
			XmlProductSqlSessionFactory.class;
	
	private static SqlSessionFactory sqlSessionFactory =null;
	private XmlProductSqlSessionFactory(){};
	
	public static SqlSessionFactory getSqlSessionFactory(){
		
		synchronized(LOCK){
			if(sqlSessionFactory != null){
				return sqlSessionFactory;
			}
			String resource = "conf/chapter3/mybatis-config.xml";
			InputStream inputStream;
			try{
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory =
						new SqlSessionFactoryBuilder().build(inputStream);
				
			}catch(IOException e){
				e.printStackTrace();
				return null;
			}
			return sqlSessionFactory;
		}
	}

	public static SqlSession openSqlSession(){
		if(sqlSessionFactory == null){
			getSqlSessionFactory();
		}
		
		return sqlSessionFactory.openSession();
	}
	
	
	
	
	
}
