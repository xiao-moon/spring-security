package com.moon.mybatisplus.service;

import com.moon.mybatisplus.domain.User;

import java.util.List;

/**
 * @program: spring-security-demo
 * @description:
 * @author: 小月
 * @create: 2019-12-17 18:45
 **/
public interface UserService {
    /**
    *@Description: 查询
    *@Author: 小月
    *@date: 
    */
    List<User> selUser();

    List<User> selUserPage();
    
    /**
    *@Description: 修改
    *@Author: 小月
    *@date: 
    */
    void updUser();
    
    /**
    *@Description: 添加
    *@Author: 小月
    *@date: 
    */
    void insUser();
    
    /**
    *@Description: 删除
    *@Author: 小月
    *@date: 
    */
    void delUser();
    
}
