package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.vo.Animal;

import java.util.List;

public interface AnimalRepository {
    List<Animal> getAll();

    Animal save(Animal animal);
}
