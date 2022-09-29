package com.example.service.domain.animal;

import com.example.service.model.Animal;

import java.util.List;

public class AnimalDomain {

    private final AnimalRepository repository;
    private final RandomAnimalGenerator randomAnimalGenerator;

    public AnimalDomain(AnimalRepository repository, RandomAnimalGenerator randomAnimalGenerator) {
        this.repository = repository;
        this.randomAnimalGenerator = randomAnimalGenerator;
    }

    public List<Animal> getAnimals() {
        return this.repository.getAll();
    }

    public Animal createAnimal() {
        Animal animal = this.randomAnimalGenerator.getAnimalRandom();
        return this.repository.save(animal).orElseThrow(() -> new IllegalStateException("The animal could not be inserted"));
    }
}
