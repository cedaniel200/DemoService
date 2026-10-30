package com.cedaniel200.service.domain.vo;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;

public record Animal(String type, String name) {

    public Animal {
        if (type == null || type.isBlank()) {
            throw new InvalidArgumentException("type no puede estar vacío");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidArgumentException("name no puede estar vacío");
        }
    }
}
