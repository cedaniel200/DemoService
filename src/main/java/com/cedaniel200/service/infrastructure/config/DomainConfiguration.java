package com.cedaniel200.service.infrastructure.config;

import com.cedaniel200.service.application.animal.AnimalApplicationService;
import com.cedaniel200.service.application.random.RandomApplicationService;
import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.animal.RandomAnimalGenerator;
import com.cedaniel200.service.domain.animal.RandomAnimalGeneratorDefault;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import com.cedaniel200.service.domain.random.RandomNumberGeneratorDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public RandomNumberGenerator randomNumberGenerator() {
        return new RandomNumberGeneratorDefault();
    }

    @Bean
    public RandomAnimalGenerator randomAnimalGenerator(RandomNumberGenerator randomNumberGenerator) {
        return new RandomAnimalGeneratorDefault(randomNumberGenerator);
    }

    @Bean
    public RandomApplicationService randomApplicationService(RandomNumberGenerator randomNumberGenerator) {
        return new RandomApplicationService(randomNumberGenerator);
    }

    @Bean
    public AnimalApplicationService animalApplicationService(AnimalRepository animalRepository,
                                                             RandomAnimalGenerator randomAnimalGenerator) {
        return new AnimalApplicationService(animalRepository, randomAnimalGenerator);
    }
}
