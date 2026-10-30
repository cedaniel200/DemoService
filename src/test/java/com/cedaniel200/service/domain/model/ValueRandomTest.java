package com.cedaniel200.service.domain.model;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValueRandomTest {

    @Test
    void acceptsZero() {
        assertEquals(0, new ValueRandom(0).value());
    }

    @Test
    void rejectsNegative() {
        assertThrows(InvalidArgumentException.class, () -> new ValueRandom(-1));
    }
}
