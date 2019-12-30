package com.moon.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: spring-security-demo
 * @description: 添加plus组件
 * @author: 小月
 * @create: 2019-12-17 19:28
 **/
@Configuration
public class MybatisPlusConfig {
    /**
    *@Description: 配置分页组件
    *@Author: 小月
    *@date:
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    /**
    *@Description: 配置逻辑删除组件
    *@Author: 小月
    *@date: 
    */
//    @Bean
//    public ISqlInjector iSqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 配置mybatis的分页插件pageHelper
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }





}
