package com.moon.core.social.qq.connect;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-05 16:51
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        /**
     *    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
     *         MultiValueMap<String, String> params = new LinkedMultiValueMap();
     *         if (this.useParametersForClientAuthentication) {
     *             params.set("client_id", this.clientId);
     *             params.set("client_secret", this.clientSecret);
     *         }
     *
     *         params.set("code", authorizationCode);
     *         params.set("redirect_uri", redirectUri);
     *         params.set("grant_type", "authorization_code");
     *         if (additionalParameters != null) {
     *             params.putAll(additionalParameters);
         *         }
         */
        //设置为true才会带上这些属性
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    /**
     *把请求的格式按照QQ的标准进行自定义解析
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        logger.info("获取accessToken的响应:"+responseStr);
        String[] items = StringUtils.split(responseStr,"&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expireIn = Long.parseLong(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[1],"=");

        return new AccessGrant(accessToken,null,refreshToken,expireIn);
    }
}
