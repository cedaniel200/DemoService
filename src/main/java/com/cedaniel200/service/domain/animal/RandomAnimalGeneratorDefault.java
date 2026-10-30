package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;

import java.util.List;

public class RandomAnimalGeneratorDefault implements RandomAnimalGenerator {

    private static final List<String> TYPES = List.of(
            "Vaca", "Perro", "Gato", "Grillo", "Mariposa", "Oso", "Gallo", "Canario");
    private static final List<String> NAMES = List.of(
            "Pepe", "Luis", "Lola", "Claudio", "Piolin", "Tom", "Jerry", "Yogui");

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomAnimalGeneratorDefault(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public Animal getAnimalRandom() {
        int typeIndex = randomNumberGenerator.getNumberRandom(TYPES.size() - 1).value();
        int nameIndex = randomNumberGenerator.getNumberRandom(NAMES.size() - 1).value();
        return new Animal(TYPES.get(typeIndex), NAMES.get(nameIndex));
    }
}
