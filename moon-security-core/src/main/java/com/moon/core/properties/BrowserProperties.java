package com.moon.core.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: spring-security-demo
 * @description:
 * @author: 小月
 * @create: 2019-12-20 00:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserProperties {

    private String loginPage = "/moon-login.html";//设置默认登录页,设置了前缀之后，只要后缀能和yml文件匹配上即可注入值

    private LoginType loginType = LoginType.JSON;

    private String signUp = "/signUp.html";

}
