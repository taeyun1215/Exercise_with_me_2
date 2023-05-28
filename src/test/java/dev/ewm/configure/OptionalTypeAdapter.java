package dev.ewm.configure;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;


public class OptionalTypeAdapter implements JsonSerializer<Optional<?>>, JsonDeserializer<Optional<?>> {

    @Override
    public JsonElement serialize(Optional<?> optional, Type type, JsonSerializationContext context) {
        return context.serialize(optional.orElse(null));
    }

    @Override
    public Optional<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        return Optional.ofNullable(context.deserialize(jsonElement, getOptionalType(type)));
    }

    private Type getOptionalType(Type type) {
        return ((ParameterizedType) type).getActualTypeArguments()[0];
    }
}