import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
         session = factory.openSession(true);//自动提交事物
         userDao=session.getMapper(IUserDao.class);

    }


    @After
    public void finish() throws IOException {
        //释放资源
        session.close();
        in.close();

    }



    /**
     * 测试查询所有用户信息
     */
    @Test
    public void testFandAll(){
         List<User> users = userDao.fandAll();
        for ( User user :users){

            System.err.println(user);
        }
    }

        /**
         * 测试保存用户信息
         */
    @Test
    public void testSaveUser(){
            User user = new User();
            user.setUsername("zhangsan");
            user.setAddress("福州");
            user.setSex("男");
            user.setBirthday(new Date());
            userDao.saveUser(user);
    }





}
