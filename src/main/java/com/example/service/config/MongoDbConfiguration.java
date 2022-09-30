package com.example.service.config;

import com.example.service.persistence.codec.AnimalCollectionCodec;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.service.persistence.MongoDB.DATABASE_NAME;

@Configuration
public class MongoDbConfiguration {

    private static final String MONGO_CONNECTION_FORMAT = "mongodb://%s:%s@%s:27017/%s?authSource=admin";

    @Bean
    public CodecRegistry providesCodecRegistryInstance() {
        return CodecRegistries.fromCodecs(new AnimalCollectionCodec(),
                new UuidCodec(UuidRepresentation.STANDARD));
    }

    @Bean
    public MongoClient providesMongoClientInstance(CodecRegistry codecRegistry) {
        String connection = String.format(MONGO_CONNECTION_FORMAT, "root", "123456789", "mongo-db", DATABASE_NAME);

        MongoClientSettings newClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connection))
                .codecRegistry(codecRegistry).build();
        return MongoClients.create(newClientSettings);
    }

}