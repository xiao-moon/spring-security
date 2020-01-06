package com.moon.core.kaptcha.filter;

import com.moon.core.kaptcha.controller.KaptchaController;
import com.moon.core.kaptcha.exception.KaptchaException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Description:图片验证码过滤器
 * @Author：xiaojiaxin
 * @Date：2019-12-31 15:49
 */

/**
 * OncePerRequestFilter:保证自定义的过滤器只会被调用一次，
 * 继承的过滤器必须是springsecurity提供的过滤器
 */
@Data
public class KaptchaFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/form", request.getRequestURI())
                && StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate(request);
            } catch (KaptchaException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        //放行
        filterChain.doFilter(request, response);
    }

    //校验的逻辑
    public void validate(HttpServletRequest request) {

        //获取验证码和失效时间
        String validateCode = (String) request.getSession().getAttribute(KaptchaController.IMAGE_VALIDATE);
        Long expireTime = (Long) request.getSession().getAttribute(KaptchaController.EXPIRE_TIME);
        String codeInRequest = request.getParameter("code");

        //将验证码和失效时间的session移除
        request.getSession().removeAttribute(KaptchaController.IMAGE_VALIDATE);
        request.getSession().removeAttribute(KaptchaController.EXPIRE_TIME);

        if (validateCode == null) {
            throw new KaptchaException("验证码不存在");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new KaptchaException("验证码的值不能为空");
        }
        if (expireTime < (LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())) {
            throw new KaptchaException("验证码已过期");
        }
        if (!StringUtils.equals(validateCode, codeInRequest)) {
            throw new KaptchaException("验证码不匹配");
        }

    }

}
