package com.moon.core.kaptcha.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2019-12-31 15:52
 */
public class KaptchaException extends AuthenticationException {

    private static final long serialVersionUID = -6612273962967366500L;

    public KaptchaException(String msg) {
        super(msg);
    }
}