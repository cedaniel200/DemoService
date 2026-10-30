package com.cedaniel200.service.domain.vo;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalCatalogTest {

    @Test
    void acceptsValidLists() {
        AnimalCatalog catalog = new AnimalCatalog(List.of("Perro"), List.of("Luis"));

        assertEquals(List.of("Perro"), catalog.types());
    }

    @Test
    void rejectsEmptyTypes() {
        assertThrows(InvalidArgumentException.class,
                () -> new AnimalCatalog(List.of(), List.of("Luis")));
    }

    @Test
    void rejectsBlankNames() {
        assertThrows(InvalidArgumentException.class,
                () -> new AnimalCatalog(List.of("Perro"), List.of(" ")));
    }
}
