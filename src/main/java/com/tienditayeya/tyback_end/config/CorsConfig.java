package com.tienditayeya.tyback_end.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Value("${app.frontend-origins:http://127.0.0.1:5500,http://127.0.0.1:5501,http://127.0.0.1:5502,http://127.0.0.1:5503,http://localhost:5500,http://localhost:5501,http://localhost:5502,http://localhost:5503}")
    private String frontendOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String[] origins = java.util.Arrays.stream(frontendOrigins.split(","))
                        .map(String::trim).filter(s -> !s.isBlank()).toArray(String[]::new);
                registry.addMapping("/**")
                        .allowedOrigins(origins)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "X-Session-Token")
                        .maxAge(3600);
            }
        };
    }
}
