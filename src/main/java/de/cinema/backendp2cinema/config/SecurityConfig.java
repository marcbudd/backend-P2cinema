package de.cinema.backendp2cinema.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
                "https://oscarcinema-dhbwproject.de/*",
                "https://oscarcinema-dhbwproject.de/",
                "http://oscarcinema-dhbwproject.de/*",
                "http://oscarcinema-dhbwproject.de/",
                "https://localhost/",
                "https://localhost:4200/",
                "http://localhost/",
                "http://localhost:4200/"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
