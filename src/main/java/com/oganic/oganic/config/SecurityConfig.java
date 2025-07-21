package com.oganic.oganic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS (make sure you also define a CORS config bean)
            .and()
            .csrf().disable() // Disable CSRF (only safe for token-based APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // public APIs
                .anyRequest().authenticated()                   // others require auth
            );

        return http.build();
    }
}
