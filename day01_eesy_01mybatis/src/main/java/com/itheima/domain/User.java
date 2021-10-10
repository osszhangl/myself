package com.itheima.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2021/7/19.
 */
@ApiModel(value = "数据封装实体类型对象")
public class User {

    @ApiModelProperty(name = "id",value = "用户id")
    private Integer id;
    @ApiModelProperty(name ="usernanme",value = "用户名")
    private String username;
    @ApiModelProperty(name ="address",value = "用户地址")
    private String address;
    @ApiModelProperty(name ="sex",value = "性别")
    private String sex;
    @ApiModelProperty(name ="birthday",value = "出生日期")
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
