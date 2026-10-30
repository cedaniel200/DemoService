package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.animal.RandomAnimalGenerator;
import com.cedaniel200.service.domain.model.Animal;

import java.util.List;

public class AnimalApplicationService implements CreateAnimalUseCase, GetAnimalsUseCase {

    private final AnimalRepository repository;
    private final RandomAnimalGenerator randomAnimalGenerator;

    public AnimalApplicationService(AnimalRepository repository, RandomAnimalGenerator randomAnimalGenerator) {
        this.repository = repository;
        this.randomAnimalGenerator = randomAnimalGenerator;
    }

    @Override
    public AnimalResult createAnimal() {
        Animal animal = repository.save(randomAnimalGenerator.getAnimalRandom());
        return AnimalResult.from(animal);
    }

    @Override
    public List<AnimalResult> getAnimals() {
        return repository.getAll().stream()
                .map(AnimalResult::from)
                .toList();
    }
}
