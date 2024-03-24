package org.sziit.presentation.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.hutool.core.text.CharSequenceUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.sziit.presentation.exception.UnauthorizedException;
import org.sziit.presentation.utils.StpAdminUtil;
import org.sziit.presentation.utils.StpUserUtil;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 15:32
 */
@Log4j2
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 开启Sa-Token的继承注解功能，这样就可以使用@CheckUserLogin和@CheckAdminLogin注解了 ヾ(≧▽≦*)o
    @PostConstruct
    public void rewriteSaStrategy() {
        SaStrategy.instance.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 校验拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 指定一条 match 规则
//                    SaRouter
//                            //在Spring MVC中，之前是通过AntPathMatcher来分析路径的，
//                            // 但是从Spring 5.3.0开始改为使用WebFlux中引入的PathPatternParser, 也就是说不再支持"**"这种通配符
//                            .match("/**")    // 拦截的 path 列表，可以写多个 */
//                            .notMatch("/login",
//                                    "/back/login",
//                                    // swagger文档配置
//                                    "/*.html",
//                                    "/*/api-docs",
//                                    "/doc.html",
//                                    "/doc.html/*",
//                                    "/doc.*",
//                                    "/swagger-ui.*",
//                                    "/swagger-resources",
//                                    "/webjars/*",
//                                    "/v2/api-docs/*",
//                                    "/v3/api-docs/*,",
//                                    "/doc.html#/user接口文档/*",
//                                    "/doc.html#/admin接口文档/*")        // 排除掉的 path 列表，可以写多个
//                            .check(r -> {
//                                if (!StpUserUtil.isLogin() && !StpUtil.isLogin()){
//                                    throw new UnauthorizedException("[UnauthorizedException]: 用户未登录");
//                                }
//                            });        // 要执行的校验动作，可以写完整的 lambda 表达式


                    /**
                     * Member路由
                     */
                    SaRouter.match(
                                    "/myArtwork/**",
                                    "/transaction/**",
                                    "/storage/**",
                                    "/notice/**",
                                    "/member/**",
                                    "/collection/**")
                            .notMatch(
                                    "/login",
                                    "/quickLogin",
                                    "/back/login"
                            )
                            .check(r -> {
                                if (Boolean.FALSE.equals(StpUserUtil.isLogin())) {
                                    throw new UnauthorizedException("[UnauthorizedException]: 用户未登录");
                                }
                            });
                    /**
                     * 共有路由
                     */
                    SaRouter.match("/dictconfig/**").check(r -> {
                        if (Boolean.FALSE.equals(StpUserUtil.isLogin() || StpAdminUtil.isLogin())) {
                            throw new UnauthorizedException("[UnauthorizedException]: 用户未登录");
                        }
                    });
                    /**
                     * Admin路由
                     */
                    SaRouter.match("/back/**").check(r -> {
                        if (Boolean.FALSE.equals(StpAdminUtil.isLogin())) {
                            throw new UnauthorizedException("[UnauthorizedException]: 管理员未登录");
                        }
                    });
                    // 根据路由划分模块，不同模块不同鉴权
                    SaRouter.match("/back/**", r -> StpAdminUtil.checkPermission("admin"));
                    SaRouter.match("/myArtwork/**", r -> StpUserUtil.checkPermission("user.myArtwork"));
                    SaRouter.match("/transaction/**", r -> StpUserUtil.checkPermission("user.transaction"));
                    SaRouter.match("/storage/**", r -> StpUserUtil.checkPermission("user.storage"));
                    SaRouter.match("/notice/**", r -> StpUserUtil.checkPermission("user.notice"));
                    SaRouter.match("/member/**", r -> StpUserUtil.checkPermission("user.member"));
                    SaRouter.match("/dictconfig/**", r -> StpUserUtil.checkPermission("user.dictconfig"));
                    SaRouter.match("/collection/**", r -> StpUserUtil.checkPermission("user.collection"));
                    // 甚至你可以随意的写一个打印语句
                    SaRouter.match("/**", r ->
                    {
                        if (StpUserUtil.isLogin()) {
                            log.info(CharSequenceUtil.format("[{}]:用户访问", StpUserUtil.getLoginIdDefaultNull()));
                        }
                        if (StpAdminUtil.isLogin()) {
                            log.info(CharSequenceUtil.format("[{}]:管理员访问", StpAdminUtil.getLoginIdDefaultNull()));
                        }
                    });
                })).addPathPatterns("/**")
                // "/error"用于给404错误放行
                .excludePathPatterns("/test/",
                        "/error",
                        "/back/login",
                        "/login",
                        "/quickLogin",
                        "/back/logout",
                        "/logout",
                        // swagger文档配置
                        "/*.html",
                        "/**/api-docs",
                        "/doc.html",
                        "/doc.html/**",
                        "/doc.*",
                        "/swagger-ui.*",
                        "/swagger-resources",
                        "/webjars/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**");
    }
}
