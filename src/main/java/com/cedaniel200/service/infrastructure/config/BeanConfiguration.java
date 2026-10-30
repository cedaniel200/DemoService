package com.cedaniel200.service.infrastructure.config;

import com.cedaniel200.service.application.animal.AnimalApplicationService;
import com.cedaniel200.service.application.random.RandomApplicationService;
import com.cedaniel200.service.domain.animal.AnimalCatalog;
import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.animal.RandomAnimalGenerator;
import com.cedaniel200.service.domain.animal.RandomAnimalGeneratorDefault;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import com.cedaniel200.service.infrastructure.random.RandomNumberGeneratorDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfiguration {

    @Bean
    public RandomNumberGenerator randomNumberGenerator() {
        return new RandomNumberGeneratorDefault();
    }

    @Bean
    public AnimalCatalog animalCatalog() {
        return new AnimalCatalog(
                List.of("Vaca", "Perro", "Gato", "Grillo", "Mariposa", "Oso", "Gallo", "Canario"),
                List.of("Pepe", "Luis", "Lola", "Claudio", "Piolin", "Tom", "Jerry", "Yogui"));
    }

    @Bean
    public RandomAnimalGenerator randomAnimalGenerator(RandomNumberGenerator randomNumberGenerator,
                                                       AnimalCatalog animalCatalog) {
        return new RandomAnimalGeneratorDefault(randomNumberGenerator, animalCatalog);
    }

    @Bean
    public AnimalApplicationService animalApplicationService(AnimalRepository animalRepository,
                                                             RandomAnimalGenerator randomAnimalGenerator) {
        return new AnimalApplicationService(animalRepository, randomAnimalGenerator);
    }

    @Bean
    public RandomApplicationService randomApplicationService(RandomNumberGenerator randomNumberGenerator) {
        return new RandomApplicationService(randomNumberGenerator);
    }
}
