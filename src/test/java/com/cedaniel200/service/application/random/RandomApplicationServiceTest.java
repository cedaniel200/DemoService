package com.cedaniel200.service.application.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomApplicationServiceTest {

    @Test
    void rejectsNegativeBound() {
        RandomApplicationService service = new RandomApplicationService(bound -> {
            throw new AssertionError("no debería llamar al generador");
        });

        assertThrows(IllegalArgumentException.class, () -> service.getRandomNumber(-1));
    }
}
