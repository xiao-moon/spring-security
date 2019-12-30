package com.moon.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2019-12-25 8:56
 */
@RestController("user")
@RequestMapping("/user/api")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello,user";
    }

}
