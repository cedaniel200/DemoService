package com.cedaniel200.service.infrastructure.persistence;

public class MongoDB {

    public static final String MONGO_CONNECTION_FORMAT = "mongodb://%s:%s@%s:27017/%s?authSource=admin";
    public static final String DATABASE_NAME = "stupservicedb";

    private MongoDB() {
    }
}
