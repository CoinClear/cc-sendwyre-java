package io.coinclear.sendwyre.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectMapperProvider {

    private static final ObjectMapper defaultObjectMapper;

    static {
        defaultObjectMapper = configureDefaultMapper();
    }

    private static ObjectMapper configureDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();

        result.registerModule(module);

        return result;
    }

    /**
     * Returns the default configuration for an object mapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getDefaultMapper() {
        return defaultObjectMapper;
    }
}