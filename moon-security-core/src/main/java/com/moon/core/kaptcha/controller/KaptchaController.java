package com.moon.core.kaptcha.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Description:
 * @Author：xiaojiaxin
 * @Date：2019-12-31 14:23
 */
@Controller
public class KaptchaController {
    public final static String IMAGE_VALIDATE="kaptchaCode";
    public final static String EXPIRE_TIME="expireTime";

    @Autowired
    private DefaultKaptcha kaptcha;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/kaptcha/image")
    @ResponseBody
    public void kaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //生成验证码
        String kaptchaCode = kaptcha.createText();
        long expireTime = LocalDateTime.now().plusSeconds(60).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        logger.info("图形验证码为:"+kaptchaCode);
        logger.info("过期时间毫秒值为:"+expireTime);

        HttpSession session = request.getSession();
        //将验证码保存到session中
        session.setAttribute(IMAGE_VALIDATE,kaptchaCode);
        //将过期时间保存到session中
        session.setAttribute(EXPIRE_TIME,expireTime);

        BufferedImage image = kaptcha.createImage(kaptchaCode);
        ImageIO.write(image,"jpg",stream);

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        out.write(stream.toByteArray());
        out.flush();
        out.close();
    }

}
