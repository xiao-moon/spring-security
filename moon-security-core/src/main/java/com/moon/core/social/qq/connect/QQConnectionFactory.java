package com.moon.core.social.qq.connect;

import com.moon.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-04 15:51
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * providerId:提供商的唯一标识
     * serviceProvider:即QQServiceProvider
     * apiAdapter:即QQAdapter
     */
    public QQConnectionFactory(String providerId, String appId , String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
