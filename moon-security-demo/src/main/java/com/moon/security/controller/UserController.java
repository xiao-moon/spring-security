package com.moon.security.controller;

import com.moon.browser.support.SocialUserInfo;
import com.moon.mybatisplus.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2019-12-25 8:56
 */
@RestController("user")
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private ProviderSignInUtils signInUtils;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")
    public String hello(){
        return "hello,user";
    }


    @PostMapping("/regist")//该映射地址不需要拦截
    public String regist(User user,HttpServletRequest request){
        //不管是注册用户还是绑定用户，都会拿到一个唯一标识
        String userId = user.getName();
        //将用户信息插入到数据库表UserConnection中，并关联userId
        signInUtils.doPostSignUp(userId,new ServletWebRequest(request));
        logger.info("注册用户");
        return "注册成功";
    }




    @GetMapping("/social/get/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connectionFromSession = signInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        userInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        userInfo.setNickname(connectionFromSession.getDisplayName());
        userInfo.setHeadImg(connectionFromSession.getImageUrl());
        return userInfo;
    }


}
