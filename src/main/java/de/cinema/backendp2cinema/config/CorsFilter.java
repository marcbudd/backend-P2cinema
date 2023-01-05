package de.cinema.backendp2cinema.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

public class CorsFilter extends org.springframework.web.filter.CorsFilter {


    public CorsFilter() {
        super(configurationSource());
    }


    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200",
                "https://oscarcinema-dhbwproject.de/",
                "https://oscarcinema-dhbwproject.de/*",
                "http://oscarcinema-dhbwproject.de/",
                "http://oscarcinema-dhbwproject.de/*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
