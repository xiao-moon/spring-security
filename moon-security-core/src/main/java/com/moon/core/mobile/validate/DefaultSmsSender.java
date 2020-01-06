package com.moon.core.mobile.validate;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:发送短信验证码的实现类
 * @Author：xiaojiaxin
 * @Date：2020-01-01 23:04
 */
@Service
public class DefaultSmsSender implements SmsSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sender(String mobile) {
        String validateCode = RandomStringUtils.randomNumeric(4);
        logger.info("已向"+mobile+"发送验证码:"+validateCode);
        return validateCode;
    }
}
