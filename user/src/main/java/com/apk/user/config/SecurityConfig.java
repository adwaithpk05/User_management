package com.apk.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // BCrypt password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // -------------------------
                        // PUBLIC PAGES
                        // -------------------------
                        .requestMatchers(
                                "/entry",
                                "/admin/login",
                                "/user/login",
                                "/user/signup",

                                // -------------------------
                                // USERS PAGES
                                // -------------------------
                                "/users/**",

                                // -------------------------
                                // STATIC RESOURCES
                                // -------------------------
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Other pages require authentication
                        .anyRequest().authenticated()
                )

                // -------------------------
                // LOGIN
                // -------------------------
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .permitAll()
                )

                // -------------------------
                // LOGOUT
                // -------------------------
                .logout(logout -> logout
                        .logoutSuccessUrl("/entry")
                        .permitAll()
                );

        return http.build();
    }
}