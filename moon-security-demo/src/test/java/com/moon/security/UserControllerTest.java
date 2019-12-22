package com.moon.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @program: spring-security-demo
 * @description: 测试用例
 * @author: 小月
 * @create: 2019-12-17 15:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    //伪造的mvc环境
    private MockMvc mockMvc;

    @Before  //会在执行测试用例之前去执行
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    $.length()的用法：$表示整个json的文档，详情可以去github查看jsonPath
    @Test
    public void  querySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")//请求地址
                .param("username","xiaoyue")//请求参数
                .contentType(MediaType.APPLICATION_JSON_UTF8))//contentType
                .andExpect(MockMvcResultMatchers.status().isOk())//对请求结果的期望
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));//返回的长度为3
    }



}
