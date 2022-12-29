package de.cinema.backendp2cinema.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SecurityConfig {

    @Bean
    public MyCorsFilter corsFilter() {
        return new MyCorsFilter();
    }
}
