package ru.otus.kholudeev.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext dsContext) throws IOException {
        if (!JsonToken.VALUE_STRING.equals(jsonParser.getCurrentToken()))
            throw new IOException("Дата должна передаваться в виде строки формата \"yyyy-mm-dd\"");
        return LocalDate.parse(jsonParser.getValueAsString());
    }
}
