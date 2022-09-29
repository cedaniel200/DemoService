package com.example.service.domain.random;

import com.example.service.model.ValueRandom;

public interface RandomNumberGenerator {
    ValueRandom getNumberRandom(int bound);
}
