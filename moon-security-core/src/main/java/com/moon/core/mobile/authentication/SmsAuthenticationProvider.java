package com.moon.core.mobile.authentication;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-02 11:38
 */
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
        Object principal = smsAuthenticationToken.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) principal);
        if(userDetails == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsAuthenticationToken smsAuthenticationToken1 = new SmsAuthenticationToken((String) principal,userDetails.getAuthorities());
        smsAuthenticationToken.getDetails();
        smsAuthenticationToken1.setDetails(smsAuthenticationToken.getDetails());
        return smsAuthenticationToken1;
    }

    //用于authenticationManager匹配对应的provider，根据token来匹配的
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
