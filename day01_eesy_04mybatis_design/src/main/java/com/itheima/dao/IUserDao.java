package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.mybatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2021/7/19.
 */
public interface IUserDao {

    /**
     * 查询所有的用户信息
     * @return
     */
//    public List<User> fandAll();


    /**
     * 采用注解的方式
     * @return
     */
    @Select("select *  from user")
    public List<User> findAll();

}
