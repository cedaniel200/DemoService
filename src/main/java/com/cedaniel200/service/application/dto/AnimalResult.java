package com.cedaniel200.service.application.dto;

import com.cedaniel200.service.domain.entity.Animal;

public record AnimalResult(String id, String type, String name) {

    public static AnimalResult from(Animal animal) {
        return new AnimalResult(animal.id(), animal.type(), animal.name());
    }
}
