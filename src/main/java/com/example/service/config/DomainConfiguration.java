package com.example.service.config;

import com.example.service.domain.RandomNumberGenerator;
import com.example.service.domain.RandomNumberGeneratorDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public RandomNumberGenerator providesRandomNumberGeneratorDefaultInstance() {
        return new RandomNumberGeneratorDefault();
    }
}
