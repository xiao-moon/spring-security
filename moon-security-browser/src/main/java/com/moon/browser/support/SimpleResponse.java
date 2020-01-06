package com.moon.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: spring-security-demo
 * @description: 返回的错误信息
 * @author: 小月
 * @create: 2019-12-20 00:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {
    //返回的信息
    private Object content;

}
