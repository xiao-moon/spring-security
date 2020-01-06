package com.moon.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-05 20:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserInfo {

    private String providerId;//服务提供商

    private String providerUserId;//openId

    private String nickname;//昵称

    private String headImg;//用户头像

}
