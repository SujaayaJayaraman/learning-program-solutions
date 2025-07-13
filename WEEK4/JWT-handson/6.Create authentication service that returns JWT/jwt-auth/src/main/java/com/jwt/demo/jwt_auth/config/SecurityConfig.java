package com.jwt.demo.jwt_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Enable Basic Auth

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory user with username "user" and password "pwd"
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password("{noop}pwd") // {noop} = plain text password (not encoded)
                .roles("USER")
                .build()
        );
    }
}
