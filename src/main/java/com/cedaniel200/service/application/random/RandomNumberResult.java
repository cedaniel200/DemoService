package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.model.ValueRandom;

public record RandomNumberResult(int value) {

    public static RandomNumberResult from(ValueRandom value) {
        return new RandomNumberResult(value.value());
    }
}
