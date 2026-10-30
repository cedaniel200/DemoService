package com.cedaniel200.service.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataAnimalRepository extends MongoRepository<AnimalDocument, String> {
}
