package org.usman.api.college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/favicon.ico"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> {}) //  enable CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/v1/login/validate").permitAll()
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new HeaderAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
