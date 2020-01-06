模块简介：
moon-security-app：app相关特定代码
moon-security-browser：浏览器安全特定代码
moon-security-core:核心业务逻辑
moon-security-demo:样例程序

一、用户认证
1、表单登录校验
    1、处理用户信息获取逻辑：
        表单提交前工作-->自定义登录页或登录跳转处理路径【BrowserSecurityController类查看】；
                       自定义路径loginProcessingUrl【随便写，但必须和表单提交路径一致】，此路径会被UsernamePasswordAuthenticationFilter处理

    2、处理用户校验逻辑
        表单提交后工作-->实现UserDetailsService接口，此接口用于处理用户登录校验，会被UsernamePasswordAuthenticationFilter调用
                       分别实现登录成功和登录失败处理器，并添加到configure中

    3、处理密码加密解密-->将BCryptPasswordEncoder添加到Bean中

    4、记住我功能的实现

    5、短信登录功能的实现
        添加类似于图片验证码的校验【校验短信验证码】
            向手机发送验证码的接口及实现类
            获取手机号码发送请求的controller处理
            过滤器

        自定义认证流程，添加到springsecurity组件中【登录】

上述总结：1、缺少代码重构
         2、提交表单后明文回显






























