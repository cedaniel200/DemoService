package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.domain.model.Animal;

public record AnimalResult(String type, String name) {

    public static AnimalResult from(Animal animal) {
        return new AnimalResult(animal.type(), animal.name());
    }
}
