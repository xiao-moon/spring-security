package com.moon.browser.config;

import com.moon.core.kaptcha.filter.KaptchaFilter;
import com.moon.core.mobile.SmsAutnenticationSecurityConfig;
import com.moon.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @program: spring-security-demo
 * @description:浏览器端表单认证--步骤
 * @author: 小月
 * @create: 2019-12-19 21:13
 **/

/**
 * 一、@EnableWebSecurity作用:
 * 1: 加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 * 2: 加载了AuthenticationConfiguration, 配置了认证信息。
 * 3：如果在启动类禁用了exclude = {SecurityAutoConfiguration.class }，则需要配置@EnableWebSecurity
 * 否则不用，因为SecurityAutoConfiguration已经帮我们配置好了@EnableWebSecurity
 * <p>
 * <p>
 * 二、WebSecurityConfigurerAdapter已经默认声明了一些安全特性
 * 1、验证所有请求。
 * 2、允许用户使用表单登录进行身份验证（Spring Security 提供了一个简单的表单登录页面）。
 * 3、允许用户使用HTTP 基本认证。
 * <p>
 * <p>
 * 三、HttpSecurity提供了很多配置相关的方法，例如：authorizeRequests()、formLogin()、httpBasic()和  csrf()
 * 调用这些方法之后，除非使用and()方法结束当前标签，上下文才会回到HttpSecurity，否则链式调用的上下文将自动进入对应的标签域。
 * <p>
 * 1、authorizeRequests()：返回了一个URL拦截注册器。我们可以调用它提供的anyanyRequest(),antMatchers()和regexMatchers()
 * 等方法来匹配系统的URL，并为其指定安全策略。
 * 1.1、antMatchers()是一个采用ANT模式的URL匹配器。即使用？匹配任意单个字符，使用*匹配0或任意数量的字符，使用**匹配0或者更多的目录。
 * 1.2、permitAll()公开匹配到的这些权限
 * 1.3、hasRole("ADMIN")匹配拥有的权限，在用hasRole()方式时不需要加上ROLE_前缀，springsecurity已经帮我们加上了，
 * 如果不希望匹配ROLE_,可以使用hasAuthority()方法
 * <p>
 * 2、formLogin()方法和httpBasic()方法都声明了需要Spring Security提供的表单认证方式，分别返回对应的配置器。
 * 其中，formLogin().loginPage（"/myLogin.html"）指定自定义的登录页/myLogin.html，
 * 同时，Spring Security会用/myLogin.html注册一个POST路由，用于接收登录请求。
 * <p>
 * 3、csrf()方法是SpringSecurity提供的跨站请求伪造防护功能，当我们继承WebSecurityConfigurerAdapter时会默认开启csrf()方法。
 * 关于csrf()方法的更多内容会在后面的章节专门探讨，以使测试进程更加顺利。
 * <p>
 * <p>
 * 四、自定义处理逻辑
 * 1、疑问：按照惯例，在发送登录请求并认证成功之后，页面会跳转回原访问页。
 * 2、在某些系统中的确是跳转回原访问页的，但在部分前后端完全分离、仅靠JSON完成所有交互的系统中，一般会在登录时返回一段 JSON 数据，
 * 告知前端成功登录成功与否，由前端决定如何处理后续逻辑，而非由服务器主动执行页面跳转。这在Spring Security中同样可以实现。
 * 3、loginProcessingUrl("/authentication/form")：指定处理登录请求的路径
 * 4、successHandler(successHandler)：登录成功时的处理逻辑  继承SavedRequestAwareAuthenticationSuccessHandler
 * 5、failureHandler(failureHandler)：登录失败时的处理逻辑  继承SimpleUrlAuthenticationFailureHandler
 * 6、表单登录配置模块提供了 successHandler（）和 failureHandler（）两个方法，分别处理登录成功和登录失败的逻辑。
 * 其中，successHandler（）方法带有一个Authentication参数，携带当前登录用户名及其角色等信息；
 * 而failureHandler（）方法携带一个AuthenticationException异常参数。
 * <p>
 * <p>
 * 五、实现一个自定义的UserDetailsService，实现用户校验功能，具体访问【MyUserDetailsService.class】
 * <p>
 * <p>
 * 六、SessionManagementConfigurer、CorsConfigurer、RememberMeConfigurer
 * 1、SpringSecurity通过SessionManagementConfigurer  来配置SessionManagement的行为。
 * 2、了解：SessionManagementConfigurer是在configure方法中将最终的SessionManagementFilter插入过滤器链来实现会话管理的。
 * http.addFilter(sessionManagementFilter);
 * 3、与SessionManagementConfigurer 类似的配置器还有 CorsConfigurer、RememberMeConfigurer 等，它们都实现了SecurityConfigurer的标准接口。
 * <p>
 * 七、添加自定义过滤器【顶级过滤器类：GenericFilterBean】，自定义过滤器一定要继承springsecurity自带的过滤器类，
 * 在configure方法中加入http.addFilterBefore或after来使用
 * 1、ConcurrentSessionFilter
 * 2、AnonymousAuthenticationFilter
 * 3、FilterChainProxy
 * 4、SecurityContextHolderAwareRequestFilter
 * 5、OncePerRequestFilter【有子类】：继承此Filter的类只会使用一次，再次使用会重新创建
 * 6、SecurityContextPersistenceFilter
 * 7、ExceptionTranslationFilter
 * 8、DigestAuthenticationFilter
 * 9、DelegatingFilterProxy【有子类】
 * 10、RequestCacheAwareFilter
 * 11、ReconnectFilter
 * 12、ResourceUrlEncodingFilter
 * 13、AbstractAuthenticationProcessingFilter【有子类】
 * 14、DefaultLoginPageGeneratingFilter
 * 15、SessionManagementFilter
 * 16、LogoutFilter
 * 17、AbstractPreAuthenticatedProcessingFilter【有子类】
 * 18、JaasApiIntegrationFilter
 * 19、SwitchUserFilter
 * 20、ChannelProcessingFilter
 * 21、RememberMeAuthenticationFilter
 * <p>
 * <p>
 * 八、使用过滤器实现图形验证码【需要将此过滤器添加到UsernamePasswordAuthenticationFilter.class的前面】
 * 1、获取图形验证码的 API：可以使用kaptcha插件，通过maven来获取；
 * 2、思路：将验证码图片刷到页面，将验证码存入session中，验证码仅仅核对session中保存的验证码与用户提交的验证码是否一致
 * 3、Filter一般会继承OncePerRequestFilter来实现，它可以确保一次请求只会通过一次该过滤器（Filter实际上并不能保证这一点）。
 * <p>
 * 个人理解：
 * 认证：
 * 1、继承WebSecurityConfigurerAdapter类，重写configure方法
 * 2、使用表单登录formLogin还是httpBasic登录，不推荐httpBasic
 * 3、登录页、登录处理、登录成功和失败处理、对某些资源放开身份认证权限【比如：登录页】、关闭跨站请求伪造功能
 * 4、实现身份认证功能：创建类实现UserDetailsService方法
 */

//@EnableWebSecurity
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties p;

    @Autowired
    private AuthenticationSuccessHandler successHandler;


    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsAutnenticationSecurityConfig smsAutnenticationSecurityConfig;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SpringSocialConfigurer moonSpringSocialConfigurer;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception {

        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        kaptchaFilter.setAuthenticationFailureHandler(failureHandler);
        //表单登录

        //
        //http.httpBasic()//身份认证（httpBasic登录，即弹出框登录）
        //将验证码处理的过滤器加到UsernamePasswordAuthenticationFilter前面
        http.addFilterBefore(kaptchaFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()//身份认证（表单登录）
                .loginPage("/anthentication/require")//自定义登录处理Controller
                .loginProcessingUrl("/authentication/form")//提交到这个请求的时候，UsernamePasswordFilter会来处理这个请求;/authentication/form这个请求是不存在的
                .successHandler(successHandler)//登录成功后会用表单登录处理器来处理
                .failureHandler(failureHandler)//登录失败后会用表单登录处理器来处理
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()//请求的授权
                //匹配到相关路径时，不需要身份认证（如果不加这个会因为无线重定向报错）,直接放行
                .antMatchers("/anthentication/require").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/moon-login.html").permitAll()
                .antMatchers("/moon-login2.html").permitAll()
                .antMatchers("/kaptcha/image").permitAll()
                .antMatchers(p.getBrowser().getLoginPage()).permitAll()
                .antMatchers("/user/api/**").permitAll()
                .antMatchers("/authentication/mobile").permitAll()
                .antMatchers("/code/mobile/**").permitAll()
                .antMatchers(p.getBrowser().getSignUp()).permitAll()
                //除了上面之外的其他任何请求//都需要身份认证
                .anyRequest()
                .authenticated()
                .and()
                //关闭跨站请求伪造功能
                .csrf().disable()
                .apply(smsAutnenticationSecurityConfig)
                .and()
                .apply(moonSpringSocialConfigurer)
                .and()
                .formLogin()
                .and()
                .sessionManagement()
                .maximumSessions(1);


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //如果不想用springsecurity自带的加密方法，可以自定义类实现PasswordEncoder接口
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        // 启动时自动创建表   如果数据库有该表，再设置为true，启动会报错
        //tokenRepositoryImpl.setCreateTableOnStartup(true);
        return tokenRepositoryImpl;
    }

}
