package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;

import java.util.List;

public record AnimalCatalog(List<String> types, List<String> names) {

    public AnimalCatalog {
        validate(types, "types");
        validate(names, "names");
        types = List.copyOf(types);
        names = List.copyOf(names);
    }

    private static void validate(List<String> values, String field) {
        if (values == null || values.isEmpty()) {
            throw new InvalidArgumentException(field + " no puede estar vacío");
        }
        if (values.stream().anyMatch(value -> value == null || value.isBlank())) {
            throw new InvalidArgumentException(field + " no puede contener valores vacíos");
        }
    }
}
