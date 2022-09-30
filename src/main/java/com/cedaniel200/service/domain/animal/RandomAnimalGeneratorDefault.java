package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import com.cedaniel200.service.domain.model.Animal;

import java.util.Arrays;
import java.util.List;

public class RandomAnimalGeneratorDefault implements RandomAnimalGenerator{

    private final List<String> types;
    private final List<String> names;

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomAnimalGeneratorDefault(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        types = Arrays.asList("Vaca", "Perro", "Cat", "Grillo", "Mariposa", "Oso", "Gallo", "Canario");
        names = Arrays.asList("Pepe", "Luis", "Lola", "Claudio", "Piolin", "Tom", "Jerry", "Yogui");
    }

    @Override
    public Animal getAnimalRandom() {
        int indexOfTypes = randomNumberGenerator.getNumberRandom(types.size() - 1).getValue();
        int indexOfNames = randomNumberGenerator.getNumberRandom(types.size() - 1).getValue();
        return new Animal(types.get(indexOfTypes), names.get(indexOfNames));
    }
}
