package com.employee.deploy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<?> corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 명시적인 도메인만 허용
        //configuration.setAllowedOriginPatterns(List.of("http://localhost:3000", "http://localhost:80","http://localhost"));
        configuration.setAllowedOriginPatterns(List.of("*"));
        // Credentials은 필요한 경우에만
        configuration.setAllowCredentials(true);
        // 필요한 헤더만 허용
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept",
                "Authorization"));
        // 필요한 메소드만 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 특정 경로에만 적용 (예: "/api/**")
        source.registerCorsConfiguration("/api/**", configuration);

        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}