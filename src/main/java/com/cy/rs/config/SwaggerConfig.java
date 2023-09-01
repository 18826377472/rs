package com.cy.rs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    /**
     * 配置swagger的Docket的bean实例
     * enable是否启动swagger
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /**RequestHandlerSelectors:配置要扫描的接口
                 * basePackage:指定要扫描的包
                 * any():扫描全部
                 * none():不扫描
                 * withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                 * withMethodAnnotation:扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.cy.rs.controller"))
                /**
                 * paths()   :过滤什么路径（拦截器）
                 */
                .build();
    }

    private ApiInfo apiInfo() {

        //作者信息
        Contact contact = new Contact("林某", "https://leetcode.cn/problemset/all/", "3400087923@qq.com");
        return new ApiInfo(
                "林某的swagger文档",
                "不早睡不改名",
                "1.0",
                "https://leetcode.cn/problemset/all/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }

}
