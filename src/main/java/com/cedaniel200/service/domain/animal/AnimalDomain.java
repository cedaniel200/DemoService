package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;

import java.util.List;

public class AnimalDomain {

    private final AnimalRepository repository;
    private final RandomAnimalGenerator randomAnimalGenerator;

    public AnimalDomain(AnimalRepository repository, RandomAnimalGenerator randomAnimalGenerator) {
        this.repository = repository;
        this.randomAnimalGenerator = randomAnimalGenerator;
    }

    public Animal createAnimal() {
        return repository.save(randomAnimalGenerator.getAnimalRandom())
                .orElseThrow(() -> new IllegalStateException("No se pudo guardar el animal"));
    }

    public List<Animal> getAnimals() {
        return repository.getAll();
    }
}
