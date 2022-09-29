package com.example.service.config;

import com.example.service.domain.animal.AnimalDomain;
import com.example.service.domain.animal.AnimalRepository;
import com.example.service.domain.animal.RandomAnimalGenerator;
import com.example.service.domain.animal.RandomAnimalGeneratorDefault;
import com.example.service.domain.random.RandomNumberGenerator;
import com.example.service.domain.random.RandomNumberGeneratorDefault;
import com.example.service.persistence.MongoAnimalRepository;
import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public AnimalRepository providesMongoAnimalRepositoryInstance(MongoClient mongoClient) {
        return new MongoAnimalRepository(mongoClient);
    }

    @Bean
    public RandomNumberGenerator providesRandomNumberGeneratorDefaultInstance() {
        return new RandomNumberGeneratorDefault();
    }

    @Bean
    public RandomAnimalGenerator providesRandomAnimalGeneratorDefaultInstance(RandomNumberGenerator randomNumberGenerator) {
        return new RandomAnimalGeneratorDefault(randomNumberGenerator);
    }

    @Bean
    public AnimalDomain providesAnimalDomainInstance(AnimalRepository animalRepository, RandomAnimalGenerator randomAnimalGenerator) {
        return new AnimalDomain(animalRepository, randomAnimalGenerator);
    }
}
