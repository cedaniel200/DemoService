package com.example.service.persistence;

import com.example.service.domain.animal.AnimalRepository;
import com.example.service.model.Animal;
import com.example.service.persistence.collection.AnimalCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.service.persistence.MongoDB.DATABASE_NAME;
import static com.example.service.persistence.collection.AnimalCollection.ANIMALS_COLLECTION;
import static com.example.service.utils.AnimalMapper.getAnimalCollectionFrom;
import static com.example.service.utils.AnimalMapper.getAnimalFrom;
import static java.util.Objects.requireNonNull;

public class MongoAnimalRepository implements AnimalRepository {

    private final MongoClient mongoClient;
    private final Logger log = Logger.getLogger(MongoAnimalRepository.class.getName());

    public MongoAnimalRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<Animal> getAll() {
        List<Animal> animals = new ArrayList<>();
        MongoCollection<AnimalCollection> collection = getCollection();
        for (AnimalCollection animal : collection.find()) animals.add(getAnimalFrom(animal));

        String message = "Get Animals: A list with " + animals.size() + " animals was obtained";
        log.info( message);

        return animals;
    }

    @Override
    public Optional<Animal> save(Animal animal) {
        MongoCollection<AnimalCollection> coleccion = getCollection();
        InsertOneResult result = coleccion.insertOne(getAnimalCollectionFrom(animal));
        printLogSave(result);
        return result.wasAcknowledged() ? Optional.of(animal) : Optional.empty();
    }

    private MongoCollection<AnimalCollection> getCollection() {
        MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
        return db.getCollection(ANIMALS_COLLECTION, AnimalCollection.class);
    }

    private void printLogSave(InsertOneResult result) {
        Level level = result.wasAcknowledged() ? Level.INFO : Level.WARNING;
        String message = result.wasAcknowledged()
                ? "Inserted the animal with id " + requireNonNull(result.getInsertedId()).asObjectId().getValue()
                : "The animal could not be inserted";
        log.log(level, message);
    }

}
