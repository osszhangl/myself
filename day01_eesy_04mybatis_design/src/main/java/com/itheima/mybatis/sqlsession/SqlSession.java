package com.itheima.mybatis.sqlsession;

/**自定义mybatis中和数据库交互的核心类，他里面可以创建dao的代理对象
 * Created by zhanglin on 2021/7/19.
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象，参数是dao的字节码
     * @param daointerfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daointerfaceClass);

    void close();
}
