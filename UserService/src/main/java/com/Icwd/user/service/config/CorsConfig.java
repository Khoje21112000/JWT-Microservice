//package com.Icwd.user.service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Configure allowed origins, headers, methods, etc.
//        config.addAllowedOrigin("*"); // Set the allowed origin, "*" allows all origins
//        config.addAllowedHeader("*"); // Set the allowed headers, "*" allows all headers
//        config.addAllowedMethod("*"); // Set the allowed HTTP methods, "*" allows all methods
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}
//
