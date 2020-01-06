package com.moon.core.mobile.validate;

import com.moon.core.kaptcha.exception.KaptchaException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description:校验验证码信息的过滤器
 * @Author：xiaojiaxin
 * @Date：2020-01-01 23:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsValidateFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/mobile", request.getRequestURI())
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
        String validateCode = (String) request.getSession().getAttribute(SmsValidateController.MOBILE_VALIDATE_CODE);
        Long expireTime = (Long) request.getSession().getAttribute(SmsValidateController.MOBILE_VALIDATE_CODE_EXPIRE_TIME);
        String codeInRequest = request.getParameter("code");

        //将验证码和失效时间的session移除
        request.getSession().removeAttribute(SmsValidateController.MOBILE_VALIDATE_CODE);
        request.getSession().removeAttribute(SmsValidateController.MOBILE_VALIDATE_CODE_EXPIRE_TIME);

        if (validateCode == null) {
            throw new SmsValidateException("验证码不存在");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new SmsValidateException("验证码的值不能为空");
        }
        if (expireTime < (LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())) {
            logger.info("短信验证码已过期");
            throw new SmsValidateException("验证码已过期");
        }
        if (!StringUtils.equals(validateCode, codeInRequest)) {
            throw new SmsValidateException("验证码不匹配");
        }
        logger.info("短信验证码通过验证");
    }
}
