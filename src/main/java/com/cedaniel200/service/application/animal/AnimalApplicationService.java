package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.animal.RandomAnimalGenerator;
import com.cedaniel200.service.domain.model.Animal;

import java.util.List;

public class AnimalApplicationService {

    private final AnimalRepository repository;
    private final RandomAnimalGenerator randomAnimalGenerator;

    public AnimalApplicationService(AnimalRepository repository, RandomAnimalGenerator randomAnimalGenerator) {
        this.repository = repository;
        this.randomAnimalGenerator = randomAnimalGenerator;
    }

    public AnimalResponse createAnimal() {
        Animal animal = repository.save(randomAnimalGenerator.getAnimalRandom())
                .orElseThrow(() -> new IllegalStateException("No se pudo guardar el animal"));
        return AnimalResponse.from(animal);
    }

    public List<AnimalResponse> getAnimals() {
        return repository.getAll().stream()
                .map(AnimalResponse::from)
                .toList();
    }
}
