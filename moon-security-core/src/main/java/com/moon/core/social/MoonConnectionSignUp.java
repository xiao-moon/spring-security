package com.moon.core.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Description:如果不需要第三方登录后跳转到注册绑定页面，实现ConnectionSignUp接口
 * @Author：xiaojiaxin
 * @Date：2020-01-05 22:00
 */
@Component
public class MoonConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //自己的注册逻辑代码，根据社交用户信息自动创建用户并返回唯一标识，唯一标识是存在UserConnection表中userId字段里的
        String displayName = connection.getDisplayName();
        return displayName;
    }
}
