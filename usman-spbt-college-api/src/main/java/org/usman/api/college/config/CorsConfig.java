package org.usman.api.college.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Slf4j
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowedOrigins(List.of("http://localhost:4200")); // your Angular app
        config.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        //config.setAllowedHeaders(List.of("Content-Type", "Authorization", "X-API-KEY"));
        config.setAllowedHeaders(List.of("*")); //  allow all headers for testing
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        log.info("--corsConfigurer called---");
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Apply to all endpoints
//                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200") // Replace with your Angular app's origins
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*")
//                        .exposedHeaders("Content-Type", "MyApp", "Authorization")
//                        .allowCredentials(true)
//                        .allowedOrigins("vip_college");
//            }
//        };
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Angular origin
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
