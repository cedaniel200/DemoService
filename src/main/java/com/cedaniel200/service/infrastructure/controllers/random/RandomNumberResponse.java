package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.application.random.RandomNumberResult;

public record RandomNumberResponse(int value) {

    public static RandomNumberResponse from(RandomNumberResult result) {
        return new RandomNumberResponse(result.value());
    }
}
