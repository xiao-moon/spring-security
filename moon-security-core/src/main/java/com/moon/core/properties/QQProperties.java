package com.moon.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2020-01-05 14:08
 */
@Data
@NoArgsConstructor
public class QQProperties{
    private String appId = "";
    private String appSecret = "";
    private String providerId = "qq"; //服务提供商的标识
    private String filterProcessesUrl = "/auth";//跳转回来重定向的url

}
