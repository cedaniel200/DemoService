package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;
import com.cedaniel200.service.domain.model.ValueRandom;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomAnimalGeneratorDefaultTest {

    @Test
    void returnsAnimalFromTheConfiguredLists() {
        RandomNumberGenerator fixed = bound -> new ValueRandom(0);

        Animal animal = new RandomAnimalGeneratorDefault(fixed).getAnimalRandom();

        assertEquals(new Animal("Vaca", "Pepe"), animal);
    }
}
