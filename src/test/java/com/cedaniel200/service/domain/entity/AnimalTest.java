package com.cedaniel200.service.domain.entity;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalTest {

    @Test
    void createGeneratesAnId() {
        Animal animal = Animal.create("Perro", "Luis");

        assertNotNull(animal.id());
        assertFalse(animal.id().isBlank());
        assertEquals("Perro", animal.type());
        assertEquals("Luis", animal.name());
    }

    @Test
    void acceptsAnExplicitId() {
        assertEquals("animal-1", new Animal("animal-1", "Perro", "Luis").id());
    }

    @Test
    void rejectsBlankId() {
        assertThrows(InvalidArgumentException.class,
                () -> new Animal(" ", "Perro", "Luis"));
    }

    @Test
    void rejectsBlankType() {
        assertThrows(InvalidArgumentException.class,
                () -> new Animal("animal-1", " ", "Luis"));
    }

    @Test
    void rejectsNullName() {
        assertThrows(InvalidArgumentException.class,
                () -> new Animal("animal-1", "Perro", null));
    }
}
