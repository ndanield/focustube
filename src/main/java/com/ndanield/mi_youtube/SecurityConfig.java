package com.ndanield.mi_youtube;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Permite acceso público a endpoints de API
                .anyRequest().authenticated() // Obliga a loguearse para ver cualquier cosa
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/", true) // Al loguearte, te manda al index.html
            );
        return http.build();
    }
}