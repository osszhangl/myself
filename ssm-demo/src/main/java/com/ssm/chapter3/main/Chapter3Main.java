package com.ssm.chapter3.main;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import com.ssm.chapter3.dao.RoleDao;
import com.ssm.chapter3.pojo.Role;
import com.ssm.chapter3.sqlsessionfactory.XmlProductSqlSessionFactory;

public class Chapter3Main {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Chapter3Main.class);
		SqlSession sqlSession = null;
		try{
			sqlSession = XmlProductSqlSessionFactory.openSqlSession();
			RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
			Role role = roleDao.getRole(1);
			log.info(role.getName());
			
			
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		
	}
}
