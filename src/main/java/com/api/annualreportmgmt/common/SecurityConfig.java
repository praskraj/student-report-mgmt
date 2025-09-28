//package com.api.annualreportmgmt.common;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .cors().and()  // Enable CORS
//            .csrf().disable() // Disable CSRF if needed
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().permitAll()  // Allow all requests (for testing)
//            );
//
//        return http.build();
//    }
//}
