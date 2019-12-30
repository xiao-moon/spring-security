package com.moon.mybatisplus.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moon.mybatisplus.domain.User;
import com.moon.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: spring-security-demo
 * @description:
 * @author: 小月
 * @create: 2019-12-17 19:04
 **/
@RequestMapping("/mybatis/plus")
@Controller()
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    /**
    *@Description: 查询
    *@Author: 小月
    *@date:
    */
    @RequestMapping("/selUser")
    public Object selUser(){
        return userService.selUser();
    }

    /**
    *@Description: pagehelper分页
    *@Author: 小月
    *@date:
    */
    @RequestMapping("/selUserPage")
    public Object selUserPage(){
        PageHelper.startPage(1,3);
        List<User> users = userService.selUser();
        PageInfo<User> p = new PageInfo<>(users);
        return p;
    }


}
