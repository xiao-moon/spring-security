package com.moon.core.mobile.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description:异常处理器
 * @Author：xiaojiaxin
 * @Date：2020-01-01 23:44
 */
public class SmsValidateException extends AuthenticationException {

    private static final long serialVersionUID = -4235087656409975780L;

    public SmsValidateException(String msg){
        super(msg);
    }


}
