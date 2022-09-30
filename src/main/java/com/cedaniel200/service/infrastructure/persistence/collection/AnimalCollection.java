package com.cedaniel200.service.infrastructure.persistence.collection;

import com.cedaniel200.service.domain.model.Animal;
import org.bson.types.ObjectId;

public class AnimalCollection extends Animal {

    public final static String ANIMALS_COLLECTION = "animals";

    public final static String ID_FIELD = "_id";
    public final static String TYPE_FIELD = "type";
    public final static String NAME_FIELD = "name";

    private final ObjectId _id;


    public AnimalCollection(ObjectId id, String type, String name) {
        super(type, name);
        _id = id;
    }

    public ObjectId get_id() {
        return _id;
    }
}
