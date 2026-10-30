package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;
import com.cedaniel200.service.domain.model.ValueRandom;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomAnimalGeneratorDefaultTest {

    @Test
    void returnsAnimalFromTheCatalog() {
        RandomNumberGenerator fixed = bound -> new ValueRandom(0);
        AnimalCatalog catalog = new AnimalCatalog(List.of("Vaca"), List.of("Pepe"));

        Animal animal = new RandomAnimalGeneratorDefault(fixed, catalog).getAnimalRandom();

        assertEquals(new Animal("Vaca", "Pepe"), animal);
    }
}
