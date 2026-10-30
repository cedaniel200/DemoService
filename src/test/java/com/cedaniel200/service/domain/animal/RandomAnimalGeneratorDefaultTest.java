package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.entity.Animal;
import com.cedaniel200.service.domain.vo.AnimalCatalog;
import com.cedaniel200.service.domain.vo.ValueRandom;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomAnimalGeneratorDefaultTest {

    @Test
    void returnsAnimalFromTheCatalog() {
        RandomNumberGenerator fixed = bound -> new ValueRandom(0);
        AnimalCatalog catalog = new AnimalCatalog(List.of("Vaca"), List.of("Pepe"));

        Animal animal = new RandomAnimalGeneratorDefault(fixed, catalog).getAnimalRandom();

        assertEquals("Vaca", animal.type());
        assertEquals("Pepe", animal.name());
        assertNotNull(animal.id());
    }
}
