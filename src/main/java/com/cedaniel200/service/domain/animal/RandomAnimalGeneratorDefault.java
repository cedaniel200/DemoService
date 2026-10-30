package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.entity.Animal;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import com.cedaniel200.service.domain.vo.AnimalCatalog;

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
        return Animal.create(animalCatalog.types().get(typeIndex), animalCatalog.names().get(nameIndex));
    }
}
