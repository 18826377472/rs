package com.cy.rs.config;

import com.cy.rs.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置白名单：存放List集合中
     * 登入注册方法
     * 静态页面
     * 主页面和登入注册页面
     */


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/user/reg");
        patterns.add("/user/login");
//        patterns.add("/swagger/**");
        patterns.add("/swagger-resources/**");
//        patterns.add("/v2/**");
        patterns.add("/swagger-ui.html/**");
        patterns.add("/webjars/**");
//        patterns.add("/swagger/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/sss").excludePathPatterns(patterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
