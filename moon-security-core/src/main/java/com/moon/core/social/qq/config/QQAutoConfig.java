package com.moon.core.social.qq.config;

import com.moon.core.properties.QQProperties;
import com.moon.core.properties.SecurityProperties;
import com.moon.core.social.lost.SocialAutoConfigurerAdapter;
import com.moon.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-05 14:17
 */
@Configuration
//设置生效条件
@ConditionalOnProperty(prefix = "moon.security.social.qq" , name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties properties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties  qqProperties = properties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId()
                                        ,qqProperties.getAppId(),qqProperties.getAppSecret());

    }
}
