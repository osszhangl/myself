package com.itheima.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanglin on 2021/7/22.
 */
@Api(tags = "用户管理模块-基本数据维护")
@RestController
public class MyController {


    @PostMapping("/post")
    @ApiOperation(value = "post的请求，根据用户名查询用户记录")
    @ApiImplicitParam(name="userName",value="查询参数",required = true,paramType = "path")
    public String post(String userName){

        return "post";
    }
    @GetMapping("/get")
    @ApiOperation(value = "根据用户的id查询用户信息")
    @ApiImplicitParam(name="userId",value = "查询参数",required = true,paramType = "path")
    public String get(Integer id){

        return "get";
    }
    @RequestMapping("/req")
    @ApiOperation(value = "根据传入的User对象，查询对应的用户数据")
    @ApiImplicitParam(name = "user",value = "查询参数封装的实体类型对象",required = true,paramType = "path")
    public String req(String user){

        return "req";
    }



}
