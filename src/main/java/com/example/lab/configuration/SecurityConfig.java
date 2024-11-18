package com.example.lab.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth-> {auth.requestMatchers(HttpMethod.GET, "/rest/users").hasRole("ADMIN")
                        .anyRequest().authenticated();})
                .httpBasic();
        return http.build();
    }
}
