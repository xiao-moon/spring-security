package com.moon.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Description:获取用户信息实现类
 * @Author：xiaojiaxin
 * @Date：2020-01-03 10:41
 */
/**
 *AbstractOAuth2ApiBinding:accessToken令牌，restTemplate帮我们向资源服务器发http请求
 * QQImpl是多实例的，需要每次用都给他实例化，否则会出现线程问题
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2/me?access_token=%s";
    //access_token交给父类AbstractOAuth2ApiBinding来处理
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    @Autowired
    private ObjectMapper objectMapper;

    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId=appId;
        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        this.openId= StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    /**
     *步骤六：获取用户信息
     */
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        QQUserInfo qqUserInfo = objectMapper.convertValue(result, QQUserInfo.class);
        qqUserInfo.setOpenId(openId);
        //QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
        logger.info("getUserInfo:"+result);
        return qqUserInfo;
    }
}
