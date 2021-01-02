package com.moon.core.mobile;

import com.moon.core.mobile.authentication.SmsAuthenticationFilter;
import com.moon.core.mobile.authentication.SmsAuthenticationProvider;
import com.moon.core.mobile.validate.SmsValidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-02 14:26
 */
@Component
public class SmsAutnenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        //将AuthenticationManager设置到过滤器中
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);

        SmsAuthenticationProvider authenticationProvider = new SmsAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);

        //短信验证码校验也可以提前放在BrowserSecurityConfig中验证
        SmsValidateFilter smsValidateFilter = new SmsValidateFilter();
        smsValidateFilter.setAuthenticationFailureHandler(failureHandler);


        http.authenticationProvider(authenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsValidateFilter, SmsAuthenticationFilter.class);

    }
}
