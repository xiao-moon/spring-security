package com.moon.security.browser;

import com.moon.security.browser.support.SimpleResponse;
import com.moon.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-security-demo
 * @description: 自定义表单校验
 * @author: 小月
 * @create: 2019-12-20 00:11
 **/
@RestController
public class BrowserSevurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //在执行跳转之前，springsecurity会把当前的请求缓存到HttpSessionRequestCache类中
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

//    @Value(value = "${moon.security.browser.loginPage}")
//    private String loginPage;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 接到http请求或数据请求后
     * 判断是否需要验证身份
     * 是：跳转到自定义的Controller中，即当前Controller
     * 在当前方法内判断：是否是html请求引发的跳转
     * 是：返回登录页面
     * 否：即请求的是资源信息，则返回401状态码和错误信息
     */
    //需要身份认证时，跳转到这里
    @RequestMapping("/anthentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                //如果引发跳转的URL是以HTML结尾，跳转到登录页
                //重定向到登录页
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowserProperties().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
