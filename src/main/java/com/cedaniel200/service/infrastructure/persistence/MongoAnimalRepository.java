package com.cedaniel200.service.infrastructure.persistence;

import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoAnimalRepository implements AnimalRepository {

    private final SpringDataAnimalRepository repository;

    public MongoAnimalRepository(SpringDataAnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Animal> getAll() {
        return repository.findAll().stream()
                .map(document -> new Animal(document.type(), document.name()))
                .toList();
    }

    @Override
    public Optional<Animal> save(Animal animal) {
        AnimalDocument saved = repository.save(new AnimalDocument(animal.type(), animal.name()));
        return Optional.of(new Animal(saved.type(), saved.name()));
    }
}
