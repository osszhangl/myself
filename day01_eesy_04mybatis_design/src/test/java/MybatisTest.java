import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2021/7/19.
 */
public class MybatisTest {

    private IUserDao userDao;
    private InputStream in ;
    private SqlSession session;

    @Before
    public void init() throws IOException {

         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                SqlSessionFactory factory = builder.build(in);
         session = factory.openSession();//自动提交事物
         userDao=session.getMapper(IUserDao.class);

    }


    @After
    public void finish() throws IOException {
        //释放资源
//        session.commit();
        session.close();

        in.close();

    }



    /**
     * 测试查询所有用户信息
     */
    @Test
    public void testFandAll(){

         List<User> users = userDao.findAll();
        for ( User user :users){

            System.err.println(user);
        }
    }






}
