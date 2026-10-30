package com.cedaniel200.service.infrastructure.controllers.dto;

import com.cedaniel200.service.application.dto.RandomNumberResult;

public record RandomNumberResponse(int value) {

    public static RandomNumberResponse from(RandomNumberResult result) {
        return new RandomNumberResponse(result.value());
    }
}
