package com.cedaniel200.service.domain.entity;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;

import java.util.UUID;

public record Animal(String id, String type, String name) {

    public Animal {
        requireText(id, "id");
        requireText(type, "type");
        requireText(name, "name");
    }

    public static Animal create(String type, String name) {
        return new Animal(UUID.randomUUID().toString(), type, name);
    }

    private static void requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new InvalidArgumentException(field + " no puede estar vacío");
        }
    }
}
