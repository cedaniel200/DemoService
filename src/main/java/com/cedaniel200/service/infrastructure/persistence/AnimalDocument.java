package com.cedaniel200.service.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "animals")
public record AnimalDocument(@Id String id, String type, String name) {
}
