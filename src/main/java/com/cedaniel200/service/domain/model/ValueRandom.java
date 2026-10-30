package com.cedaniel200.service.domain.model;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;

public record ValueRandom(int value) {

    public ValueRandom {
        if (value < 0) {
            throw new InvalidArgumentException("value debe ser mayor o igual que 0");
        }
    }
}
