package com.moon.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.security.core.properties.LoginType;
import com.moon.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-security-demo
 * @description: 自定义登录成功处理
 * @author: 小月
 * @create: 2019-12-21 15:20
 **/
@Component
//public class MoonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class MoonAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登录成功会被调用
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("MoonAuthenticationSuccessHandler:登录成功！");
        if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
            //如果是json传值方式登录，走这里
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            //否则就调用父类的方法，处理表单
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
