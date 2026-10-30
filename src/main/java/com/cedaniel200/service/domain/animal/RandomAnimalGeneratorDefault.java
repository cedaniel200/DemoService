package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;

public class RandomAnimalGeneratorDefault implements RandomAnimalGenerator {

    private final RandomNumberGenerator randomNumberGenerator;
    private final AnimalCatalog animalCatalog;

    public RandomAnimalGeneratorDefault(RandomNumberGenerator randomNumberGenerator,
                                        AnimalCatalog animalCatalog) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.animalCatalog = animalCatalog;
    }

    @Override
    public Animal getAnimalRandom() {
        int typeIndex = randomNumberGenerator
                .getNumberRandom(animalCatalog.types().size() - 1).value();
        int nameIndex = randomNumberGenerator
                .getNumberRandom(animalCatalog.names().size() - 1).value();
        return new Animal(animalCatalog.types().get(typeIndex), animalCatalog.names().get(nameIndex));
    }
}
