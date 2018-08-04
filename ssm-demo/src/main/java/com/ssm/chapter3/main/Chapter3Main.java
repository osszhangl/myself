package com.ssm.chapter3.main;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import com.ssm.chapter3.dao.RoleDao;
import com.ssm.chapter3.pojo.Role;
import com.ssm.chapter3.sqlsessionfactory.XmlProductSqlSessionFactory;
/**
 * 
 * @ClassName Chapter3Main
 * @Description 使用SqlSession访问数据库，实例
 * @Author Administrator
 * @Date 2018年8月4日 下午1:07:25
 */
public class Chapter3Main {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Chapter3Main.class);
		SqlSession sqlSession = null;
		try{
			sqlSession = XmlProductSqlSessionFactory.openSqlSession();
			RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
			Role roleInstance= new Role(6,"yumao");
			int inserNum =roleDao.insertRole(roleInstance);
			log.info("成功插入数据"+inserNum);
			Role role = roleDao.getRole(5);
			log.info(role);
			sqlSession.commit();//注意：这里因为是直接用SqlSession操作数据，需要手动提交事物不然数据库里查询不到结果。（尤其insert、updata、delete的时候）
			log.info("数据提交成功");
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		
	}
}
