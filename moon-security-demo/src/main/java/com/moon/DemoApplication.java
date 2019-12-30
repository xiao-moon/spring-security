package com.moon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-security-demo
 * @description: 启动类
 * @author: 小月
 * @create: 2019-12-17 13:27
 **/
/**SecurityAutoConfiguration：排除security自动开启的类*/
@SpringBootApplication()
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello,这里是springsecurity";
    }
}
