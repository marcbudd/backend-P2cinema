package de.cinema.backendp2cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SecurityConfig {

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
