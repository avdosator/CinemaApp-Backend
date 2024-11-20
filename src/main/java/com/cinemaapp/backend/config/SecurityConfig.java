package com.cinemaapp.backend.config;

import com.cinemaapp.backend.config.security.CustomUserDetails;
import com.cinemaapp.backend.repository.UserRepository;
import com.cinemaapp.backend.service.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            final User user = userRepository.findByEmail(email);
            return new CustomUserDetails(user);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disabled in development
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Allow all requests to all endpoints in development
                );

        return http.build();
    }
}
