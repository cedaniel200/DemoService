package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.model.ValueRandom;

public record RandomNumberResponse(int value) {

    public static RandomNumberResponse from(ValueRandom value) {
        return new RandomNumberResponse(value.value());
    }
}
