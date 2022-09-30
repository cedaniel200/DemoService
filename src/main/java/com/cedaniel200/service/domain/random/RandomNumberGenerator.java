package com.cedaniel200.service.domain.random;

import com.cedaniel200.service.domain.model.ValueRandom;

public interface RandomNumberGenerator {
    ValueRandom getNumberRandom(int bound);
}
