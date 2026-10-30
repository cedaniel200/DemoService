package com.cedaniel200.service.domain.animal;

import com.cedaniel200.service.domain.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalDomainTest {

    @Test
    void savesTheGeneratedAnimal() {
        AnimalRepository repository = new InMemoryAnimalRepository();
        RandomAnimalGenerator generator = () -> new Animal("Perro", "Luis");
        AnimalDomain domain = new AnimalDomain(repository, generator);

        Animal created = domain.createAnimal();

        assertEquals(new Animal("Perro", "Luis"), created);
        assertEquals(List.of(new Animal("Perro", "Luis")), domain.getAnimals());
    }

    private static class InMemoryAnimalRepository implements AnimalRepository {

        private final List<Animal> animals = new ArrayList<>();

        @Override
        public List<Animal> getAll() {
            return List.copyOf(animals);
        }

        @Override
        public Optional<Animal> save(Animal animal) {
            animals.add(animal);
            return Optional.of(animal);
        }
    }
}
