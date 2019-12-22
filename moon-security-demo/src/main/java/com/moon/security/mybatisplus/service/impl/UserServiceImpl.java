package com.moon.security.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moon.security.mybatisplus.domain.User;
import com.moon.security.mybatisplus.mapper.UserMapper;
import com.moon.security.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-security-demo
 * @description:
 * @author: 小月
 * @create: 2019-12-17 18:48
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

//    wapper介绍 ：
//    Wrapper ： 条件构造抽象类，最顶端父类，抽象类中提供4个方法西面贴源码展示
//    AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
//    AbstractLambdaWrapper ： Lambda 语法使用 Wrapper统一处理解析 lambda 获取 column。
//    LambdaQueryWrapper ：看名称也能明白就是用于Lambda语法使用的查询Wrapper
//    LambdaUpdateWrapper ： Lambda 更新封装Wrapper
//    QueryWrapper ： Entity 对象封装操作类，不是用lambda语法
//    UpdateWrapper ： Update 条件封装，用于Entity对象更新操作

    @Override
    public List<User> selUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id",1);
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    @Override
    public List<User> selUserPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id",1);
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }


    @Override
    public void updUser() {

    }

    @Override
    public void insUser() {

    }

    @Override
    public void delUser() {

    }
}
