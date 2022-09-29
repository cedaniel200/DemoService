package com.example.service.domain.animal;

import com.example.service.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {
    List<Animal> getAll();
    Optional<Animal> save(Animal animal);
}
