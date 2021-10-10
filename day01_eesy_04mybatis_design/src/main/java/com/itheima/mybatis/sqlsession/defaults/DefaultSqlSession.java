package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.proxy.MapperProxy;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.util.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by zhanglin on 2021/7/20.
 */
public class DefaultSqlSession  implements SqlSession{

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg=cfg;
        this.connection= DataSourceUtil.getConnection(cfg);
    }




    /**
     * 用于创建代理对象
     * @param daointerfaceClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daointerfaceClass) {
        return (T) Proxy.newProxyInstance(
                daointerfaceClass.getClassLoader(),new Class[]{daointerfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        if (connection !=null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
