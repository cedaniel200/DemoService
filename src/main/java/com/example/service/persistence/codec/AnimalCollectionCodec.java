package com.example.service.persistence.codec;

import com.example.service.persistence.collection.AnimalCollection;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import static com.example.service.persistence.collection.AnimalCollection.*;

public class AnimalCollectionCodec implements Codec<AnimalCollection> {

    @Override
    public AnimalCollection decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        AnimalCollection animal = new AnimalCollection(reader.readObjectId(ID_FIELD),
                reader.readString(TYPE_FIELD),
                reader.readString(NAME_FIELD));
        reader.readEndDocument();
        return animal;
    }

    @Override
    public void encode(BsonWriter writer, AnimalCollection value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeObjectId(ID_FIELD, value.get_id());
        writer.writeString(TYPE_FIELD, value.getType());
        writer.writeString(NAME_FIELD, value.getName());
        writer.writeEndDocument();
    }

    @Override
    public Class<AnimalCollection> getEncoderClass() {
        return AnimalCollection.class;
    }
}
