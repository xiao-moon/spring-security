package com.moon.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @program: spring-security-demo
 * @description: 实现UserDetailsService接口,处理用户信息获取和用户校验逻辑
 * @author: 小月
 * @create: 2019-12-19 22:35
 **/
@Component
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
        //根据查到的用户信息判断用户是否被冻结
        //public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
        //return new User(s,"123456",true,true,true,false,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(s,passwordEncoder.encode("123456"),true,true,true,false,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));


        //根据用户名查找用户信息
        //public User(String username, String password, Collection<? extends GrantedAuthority > authorities)
        //authorities即权限，作用是告诉springsecurity当前用户拥有哪些权限
        //return new User(s,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
