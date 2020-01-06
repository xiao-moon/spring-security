package com.moon.core.mobile.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Description:用于接收前台发送的请求，并调用发送验证码的接口发送请求，将验证码存到session中
 * @Author：xiaojiaxin
 * @Date：2020-01-01 22:55
 */
@Controller
public class SmsValidateController {

    public static final  String MOBILE_VALIDATE_CODE ="mobileValidateCode";
    public static final  String MOBILE_VALIDATE_CODE_EXPIRE_TIME ="mobileExpireTime";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SmsSender smsSender;



    @GetMapping("/code/mobile/{phoneNum}")
    @ResponseBody
    public void mobileCode(@PathVariable String phoneNum, HttpServletRequest request, HttpServletResponse response){

        String sender = smsSender.sender(phoneNum);
        HttpSession session = request.getSession();
        session.setAttribute(MOBILE_VALIDATE_CODE,sender);
        long expireTime = LocalDateTime.now().plusSeconds(60).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        session.setAttribute(MOBILE_VALIDATE_CODE_EXPIRE_TIME, expireTime);
        logger.info("短信验证码处理Controller层已被成功调用");
    }

    @RequestMapping("/authentication/mobile")
    @ResponseBody
    public String mobileAuth(HttpServletRequest request){
        logger.info("短信验证码登录成功");
        return null;
    }









}
