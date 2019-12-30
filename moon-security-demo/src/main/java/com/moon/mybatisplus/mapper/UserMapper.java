package com.moon.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.mybatisplus.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: spring-security-demo
 * @description: mybatis-plus-demo
 * @author: 小月
 * @create: 2019-12-17 18:34
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
