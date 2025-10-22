package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt bảo vệ CSRF cho API REST
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // cho phép tất cả request (chưa chặn)
                );
        return http.build();
    }
}
