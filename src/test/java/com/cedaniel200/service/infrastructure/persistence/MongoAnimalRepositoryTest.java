package com.cedaniel200.service.infrastructure.persistence;

import com.cedaniel200.service.domain.entity.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class MongoAnimalRepositoryTest {

    @Container
    @ServiceConnection
    static final MongoDBContainer MONGO = new MongoDBContainer("mongo:7.0");

    @Autowired
    private MongoAnimalRepository repository;

    @Test
    void savesAndListsAnimals() {
        Animal animal = new Animal("animal-1", "Perro", "Luis");

        repository.save(animal);

        assertThat(repository.getAll()).contains(animal);
    }
}
