package com.moon.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @program: spring-security-demo
 * @description: 验证码异常
 * @author: 小月
 * @create: 2019-12-22 01:40
 **/
public class ValidateCodeException extends AuthenticationException{

    private static final long serialVersionUID = 238095520238608882L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
