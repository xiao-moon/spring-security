package com.moon.core.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: spring-security-demo
 * @description: 配置项，用于读取yml文件的配置信息
 * @author: 小月
 * @create: 2019-12-20 00:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component  //在SecurityCoreConfig中配置了@EnableConfigurationProperties注解，就不需要配置此属性了，已经注入到spring容器中
@PropertySource("application-security.properties")
@ConfigurationProperties(prefix = "moon.security") //表示此类会读取所有以moon.security开头的配置项
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social = new SocialProperties();



}
