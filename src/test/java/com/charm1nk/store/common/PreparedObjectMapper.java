package com.charm1nk.store.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;

public class PreparedObjectMapper {
    private static ObjectMapper instance;

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper()
                    .findAndRegisterModules()
                    .disable(WRITE_DATES_AS_TIMESTAMPS)
                    .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        }
        return instance;
    }
}
