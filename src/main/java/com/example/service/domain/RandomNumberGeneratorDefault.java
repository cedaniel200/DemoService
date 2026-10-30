package com.example.service.domain;

import com.example.service.model.ValueRandom;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGeneratorDefault implements RandomNumberGenerator {

    @Override
    public ValueRandom getNumberRandom(int bound) {
        return new ValueRandom(ThreadLocalRandom.current().nextInt(bound + 1));
    }
}
