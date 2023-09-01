package com.cy.rs;

import com.github.pagehelper.PageHelper;
import org.apache.catalina.filters.CorsFilter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Properties;

@SpringBootApplication
//@MapperScan注解指定当前项目中的mapper接口路径位置，项目启动时自动加载所有接口
@MapperScan("com.cy.rs.mapper")
public class RsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsApplication.class, args);
    }

    @Bean
    PageHelper pageHelper() {
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        return pageHelper;
    }







}
