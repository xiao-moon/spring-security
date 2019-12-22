package com.moon.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @program: spring-security-demo
 * @description: 图形验证码
 * @author: 小月
 * @create: 2019-12-22 01:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {
    private BufferedImage image;//展示给用户的图片
    private String code;//随机数，存到session里面
    private LocalDateTime expireTime;//过期时间


    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }


}
