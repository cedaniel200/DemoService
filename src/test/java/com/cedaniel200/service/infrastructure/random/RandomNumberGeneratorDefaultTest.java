package com.cedaniel200.service.infrastructure.random;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberGeneratorDefaultTest {

    private final RandomNumberGenerator generator = new RandomNumberGeneratorDefault();

    @RepeatedTest(20)
    void returnsValueBetweenZeroAndBoundInclusive() {
        int bound = 10;

        int value = generator.getNumberRandom(bound).value();

        assertTrue(value >= 0 && value <= bound, "value fuera de rango: " + value);
    }

    @Test
    void returnsZeroWhenBoundIsZero() {
        assertEquals(0, generator.getNumberRandom(0).value());
    }

    @Test
    void rejectsNegativeBound() {
        assertThrows(InvalidArgumentException.class, () -> generator.getNumberRandom(-1));
    }
}
