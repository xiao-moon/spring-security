package com.moon.core.social.qq.connect;

import com.moon.core.social.qq.api.QQ;
import com.moon.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-04 15:40
 */

public class QQAdapter implements ApiAdapter<QQ> {
    /**
     *测试api是否可用
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());//获取用户昵称
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());//用户头像
        connectionValues.setProfileUrl(null);//个人主页
        connectionValues.setProviderUserId(userInfo.getOpenId());//即openid

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     *更新状态，比如通过第三方应用更新微博的状态
     */
    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
