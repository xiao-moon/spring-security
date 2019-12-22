package com.moon.security.browser;

import com.moon.security.core.properties.SecurityProperties;
import com.moon.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: spring-security-demo
 * @description:表单认证--步骤一
 * @author: 小月
 * @create: 2019-12-19 21:13
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler successHandler;


    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(failureHandler);
        //表单登录
        //http.httpBasic()//身份认证（httpBasic登录，即弹出框登录）
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//将验证码处理的过滤器加到UsernamePasswordAuthenticationFilter前面
             .formLogin()//身份认证（表单登录）
            //.loginPage("/login.html")//自定义登录页面
            .loginPage("/anthentication/require")//自定义登录处理Controller
            .loginProcessingUrl("/authentication/form")//提交到这个请求的时候，UsernamePasswordFilter会来处理这个请求
            .successHandler(successHandler)//登录成功后会用表单登录处理器来处理
            .failureHandler(failureHandler)//登录失败后会用表单登录处理器来处理
            .and()
            .authorizeRequests()//请求的授权
            .antMatchers("/login.html",securityProperties.getBrowserProperties().getLoginPage()
            ,"/code/image").permitAll()//匹配到/login.html路径时，不需要身份认证（如果不加这个会因为无线重定向报错）
            .anyRequest()//任何请求
            .authenticated()//都需要身份认证
            .and()
            .csrf().disable();//关闭跨站请求伪造功能
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //如果不想用springsecurity自带的加密方法，可以自定义类实现PasswordEncoder接口
        return new BCryptPasswordEncoder();
    }




}
