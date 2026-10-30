package com.cedaniel200.service.domain.random;

import com.cedaniel200.service.domain.model.ValueRandom;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGeneratorDefault implements RandomNumberGenerator {

    @Override
    public ValueRandom getNumberRandom(int bound) {
        return new ValueRandom(ThreadLocalRandom.current().nextInt(bound + 1));
    }
}
