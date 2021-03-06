package com.University.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfiguration {
    @Bean
    public org.springframework.web.filter.CorsFilter corsFilter() {
//1.添加CORS配置信息
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
//1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
//3) 允许的请求方式
        config.addAllowedMethod("*");
// 4）允许的头信息
        config.addAllowedHeader("*");
//2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
//3.返回新的CorsFilter.
        return new org.springframework.web.filter.CorsFilter(configSource);
    }
}
