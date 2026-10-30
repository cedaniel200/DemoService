package com.cedaniel200.service.infrastructure.controllers.dto;

import com.cedaniel200.service.application.dto.AnimalResult;

public record AnimalResponse(String id, String type, String name) {

    public static AnimalResponse from(AnimalResult result) {
        return new AnimalResponse(result.id(), result.type(), result.name());
    }
}
