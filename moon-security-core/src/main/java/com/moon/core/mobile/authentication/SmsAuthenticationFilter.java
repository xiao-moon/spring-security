package com.moon.core.mobile.authentication;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:短信认证过滤器
 * @Author：xiaojiaxin
 * @Date：2020-01-02 11:22
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String MOON_FORM_MOBILE_KEY = "mobile";
    private String mobileParameter = MOON_FORM_MOBILE_KEY;
    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    //过滤器执行流程
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }


            mobile = mobile.trim();
            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    @Nullable
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public final String getMobileParameter() {
        return this.mobileParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }


}