package com.cy.rs.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StaticResourcesConfig implements WebMvcConfigurer {
    /**
     * 自定义资源映射
     * 由于在浏览器界面上传图片，而 Spring boot 程序是感知不到的，因此需要自定义资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:upload/");


    }
}