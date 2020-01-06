package com.moon.core.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-05 16:12
 */
@Data
@AllArgsConstructor
public class MoonSpringSocialConfig extends SpringSocialConfigurer {

    private String filterProcessesUrl;


    /**
     *改变springsocial自带的filter处理的url
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)filter;
    }
}
