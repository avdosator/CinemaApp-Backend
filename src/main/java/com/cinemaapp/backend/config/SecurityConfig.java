package com.cinemaapp.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disabled in development
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                "/api/**").permitAll() // Allow all requests
                        .anyRequest().authenticated() // Other than "/api/*" should be from authenticated user
                );

        return http.build();
    }
}
