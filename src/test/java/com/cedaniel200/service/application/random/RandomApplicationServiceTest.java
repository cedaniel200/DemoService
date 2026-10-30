package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.model.ValueRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomApplicationServiceTest {

    @Test
    void returnsGeneratedNumber() {
        RandomApplicationService service = new RandomApplicationService(bound -> new ValueRandom(7));

        assertEquals(new RandomNumberResult(7), service.getRandomNumber(10));
    }

    @Test
    void rejectsNegativeBound() {
        RandomApplicationService service = new RandomApplicationService(bound -> {
            throw new AssertionError("no debería llamar al generador");
        });

        assertThrows(InvalidBoundException.class, () -> service.getRandomNumber(-1));
    }
}
