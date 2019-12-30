package com.moon.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-security-demo
 * @description: 实现UserDetailsService接口,处理用户信息获取和用户校验逻辑
 * @author: 小月
 * @create: 2019-12-19 22:35
 **/

/**
 * Spring Security支持各种来源的用户数据，包括内存、数据库、LDAP等。
 * 它们被抽象为一个UserDetailsService接口，任何实现了UserDetailsService 接口的对象都可以作为认证数据源。
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        /**
         * UserDetails需要自己实现的四个方法：
         * boolean isAccountNonExpired();校验用户是否过期
         *
         * boolean isAccountNonLocked();账户是否被锁定
         *
         * boolean isCredentialsNonExpired();密码是否过期
         *
         * boolean isEnabled();账户是否可用
         *
         * */

        /**
         * 通过数据库查询到用户登录名对应的信息，放入new User中进行校验，这里需要创建一个user表实现UserDetails接口
         * 由于权限在数据库中按照springsecurity方式是用逗号隔开存放在user表中的，如果有需要可以自定义
         * 注意每种角色对应一个GrantedAuthority
         * 一定要在自己的UserDetailsService实现类上加入@Service注解，以便被Spring Security自动发现
         * 注意：在数据库中添加角色需要加上前缀ROLE_
         * 在用hasRole()方式时不需要加上ROLE_前缀，springsecurity已经帮我们加上了
         *
         */

        //根据查到的用户信息判断用户是否被冻结
        //public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        //return new User(s,"123456",true,true,true,false,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(s,passwordEncoder.encode("123456"),true,true,true,false,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));


        //根据用户名查找用户信息
        //public User(String username, String password, Collection<? extends GrantedAuthority > authorities)
        //authorities即权限，作用是告诉springsecurity当前用户拥有哪些权限
        //return new User(s,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }

    //自行实现权限的转换
    private List<GrantedAuthority> authorities(String roles){
        return null;
    }




}
