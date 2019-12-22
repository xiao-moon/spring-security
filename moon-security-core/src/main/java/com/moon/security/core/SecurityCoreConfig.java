package com.moon.security.core;

import com.moon.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-security-demo
 * @description:
 * @author: 小月
 * @create: 2019-12-20 00:52
 **/
@Configuration
//使使用 @ConfigurationProperties 注解的类生效。,如果不配置这个，需要在SecurityProperties类中添加@Component注解
//@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

//    @Bean
//    public SecurityProperties getSecurityProperties(){
//        return new SecurityProperties();
//    }
}
