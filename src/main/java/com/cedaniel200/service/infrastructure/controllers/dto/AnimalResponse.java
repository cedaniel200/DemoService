package com.cedaniel200.service.infrastructure.controllers.dto;

import com.cedaniel200.service.application.dto.AnimalResult;

public record AnimalResponse(String type, String name) {

    public static AnimalResponse from(AnimalResult result) {
        return new AnimalResponse(result.type(), result.name());
    }
}
