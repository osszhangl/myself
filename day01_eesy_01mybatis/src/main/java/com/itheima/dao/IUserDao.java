package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2021/7/19.
 */
public interface IUserDao {

    /**
     * 查询所有的用户信息
     * @return
     */
    public List<User> fandAll();

    /**
     * 保存用户信息
     * @param user
     */
    public void saveUser(User user);


}
