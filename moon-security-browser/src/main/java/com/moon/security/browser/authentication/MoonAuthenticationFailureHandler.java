package com.moon.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.security.browser.support.SimpleResponse;
import com.moon.security.core.properties.LoginType;
import com.moon.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-security-demo
 * @description: 登录失败的处理
 * @author: 小月
 * @create: 2019-12-21 20:59
 **/
@Component
//public class MoonAuthenticationFailureHandler implements  AuthenticationFailureHandler{
public class MoonAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("MoonAuthenticationFailureHandler:登录失败！");
        if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else{
            super.onAuthenticationFailure(request,response,e);
        }
    }
}
