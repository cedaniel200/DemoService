package com.cedaniel200.service.domain.vo;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalTest {

    @Test
    void acceptsValidValues() {
        assertEquals("Perro", new Animal("Perro", "Luis").type());
    }

    @Test
    void rejectsBlankType() {
        assertThrows(InvalidArgumentException.class, () -> new Animal(" ", "Luis"));
    }

    @Test
    void rejectsNullName() {
        assertThrows(InvalidArgumentException.class, () -> new Animal("Perro", null));
    }
}
