package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {
    List<Animal> getAll();
    Optional<Animal> save(Animal animal);
}
