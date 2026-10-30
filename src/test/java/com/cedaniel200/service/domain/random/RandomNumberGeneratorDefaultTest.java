package com.cedaniel200.service.domain.random;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberGeneratorDefaultTest {

    private final RandomNumberGenerator generator = new RandomNumberGeneratorDefault();

    @RepeatedTest(20)
    void returnsValueBetweenZeroAndBoundInclusive() {
        int bound = 10;

        int value = generator.getNumberRandom(bound).value();

        assertTrue(value >= 0 && value <= bound, "value fuera de rango: " + value);
    }
}
