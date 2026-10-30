package com.cedaniel200.service.application.dto;

import com.cedaniel200.service.domain.vo.ValueRandom;

public record RandomNumberResult(int value) {

    public static RandomNumberResult from(ValueRandom value) {
        return new RandomNumberResult(value.value());
    }
}
