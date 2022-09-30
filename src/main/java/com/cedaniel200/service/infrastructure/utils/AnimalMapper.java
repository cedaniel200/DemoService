package com.cedaniel200.service.infrastructure.utils;

import com.cedaniel200.service.domain.model.Animal;
import com.cedaniel200.service.infrastructure.persistence.collection.AnimalCollection;
import org.bson.types.ObjectId;

public class AnimalMapper {

    private AnimalMapper() {
    }

    public static AnimalCollection getAnimalCollectionFrom(Animal animal) {
        return new AnimalCollection(ObjectId.get(), animal.getType(), animal.getName());
    }

    public static Animal getAnimalFrom(AnimalCollection animal) {
        if (animal != null)
            return new Animal(animal.getType(), animal.getName());
        return null;
    }
}
