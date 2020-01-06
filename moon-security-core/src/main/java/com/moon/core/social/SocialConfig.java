package com.moon.core.social;

import com.moon.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-04 15:55
 */
@Configuration
@EnableSocial//启动social配置
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties properties;

    @Autowired(required = false)
    private ConnectionSignUp signUp;

    /**
     *将获取到的服务信息保存到数据库，spring已经帮我们做好了，只需配置即可
     */
    /**
     * 建表语句：在JdbcUsersConnectionRepository类的所在位置的旁边
     * 搜索JdbcUsersConnectionRepository.sql可以查到
     * 注意：UserConnection表名是不能变的，但是可以在表名前面加前缀；比如：Moon_UserConnection
     *create table UserConnection (userId varchar(255) not null commont "业务系统用户id",
     * 	providerId varchar(255) not null commont "服务提供商id",
     * 	providerUserId varchar(255) commont "openId",
     * 	rank int not null commont "等级",
     * 	displayName varchar(255),
     * 	profileUrl varchar(512),
     * 	imageUrl varchar(512),
     * 	accessToken varchar(512) not null,
     * 	secret varchar(512),
     * 	refreshToken varchar(512),
     * 	expireTime bigint,
     * 	primary key (userId, providerId, providerUserId));
     * create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //Encryptors.noOpText():Encryptors是用来对数据库的数据进行加密解密的;noOpText()表示不做任何操作
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //repository.setTablePrefix("Moon_");//设置前缀
        //第三方登录成功后自动注册账号，不用跳转到登录页
        if(signUp!=null){
            repository.setConnectionSignUp(signUp);
        }

        return repository;
    }

    /**
     *
     */
    @Bean
    public SpringSocialConfigurer moonSocialSecurityConfig(){
        MoonSpringSocialConfig config = new MoonSpringSocialConfig(properties.getSocial().getQq().getFilterProcessesUrl());
        config.signupUrl(properties.getBrowser().getSignUp());
        return config;
    }


    @Autowired
    private ConnectionFactoryLocator locator;

    /**
     * 1、拿到springsocial的信息
     * 2、将业务信息传给springsocial
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(){
        return new ProviderSignInUtils(locator,getUsersConnectionRepository(locator));
    }




}
