package com.example.service.domain.random;

import com.example.service.model.ValueRandom;

import java.util.Random;

public class RandomNumberGeneratorDefault implements RandomNumberGenerator {

    private Random random;

    public RandomNumberGeneratorDefault() {
        random = new Random();
    }

    @Override
    public ValueRandom getNumberRandom(int bound) {
        int randomNumber = getRandom(bound);
        return new ValueRandom(randomNumber);
    }

    private int getRandom(int bound) {
        return random.nextInt(bound + 1);
    }
}
