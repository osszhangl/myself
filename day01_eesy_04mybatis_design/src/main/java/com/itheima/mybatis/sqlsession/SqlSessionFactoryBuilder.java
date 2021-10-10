package com.itheima.mybatis.sqlsession;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.itheima.mybatis.util.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建SqlsessionFactory对象
 * Created by zhanglin on 2021/7/19.
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节流来创建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg
                 = XMLConfigBuilder.loadConfiguration(config);


            return new DefaultSqlSessionFactory(cfg);
        }


}
