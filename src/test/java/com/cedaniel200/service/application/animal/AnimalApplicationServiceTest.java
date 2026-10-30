package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.domain.animal.AnimalRepository;
import com.cedaniel200.service.domain.animal.RandomAnimalGenerator;
import com.cedaniel200.service.domain.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalApplicationServiceTest {

    @Test
    void savesTheGeneratedAnimal() {
        AnimalRepository repository = new InMemoryAnimalRepository();
        RandomAnimalGenerator generator = () -> new Animal("Perro", "Luis");
        AnimalApplicationService service = new AnimalApplicationService(repository, generator);

        AnimalResult created = service.createAnimal();

        assertEquals(new AnimalResult("Perro", "Luis"), created);
        assertEquals(List.of(new AnimalResult("Perro", "Luis")), service.getAnimals());
    }

    private static class InMemoryAnimalRepository implements AnimalRepository {

        private final List<Animal> animals = new ArrayList<>();

        @Override
        public List<Animal> getAll() {
            return List.copyOf(animals);
        }

        @Override
        public Animal save(Animal animal) {
            animals.add(animal);
            return animal;
        }
    }
}
