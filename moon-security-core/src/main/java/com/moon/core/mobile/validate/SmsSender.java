package com.moon.core.mobile.validate;

/**
 * @Description:发送短信验证码的接口
 * @Author：xiaojiaxin
 * @Date：2020-01-01 22:57
 */
public interface SmsSender {

    String sender(String mobile);

}
