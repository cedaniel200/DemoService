package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.domain.model.Animal;

public record AnimalResponse(String type, String name) {

    public static AnimalResponse from(Animal animal) {
        return new AnimalResponse(animal.type(), animal.name());
    }
}
