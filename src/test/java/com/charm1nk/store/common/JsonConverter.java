package com.charm1nk.store.common;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class JsonConverter {
    private static MappingJackson2HttpMessageConverter instance;

    public static MappingJackson2HttpMessageConverter getInstance() {
        if (instance == null) {
            final var converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(PreparedObjectMapper.getInstance());
            instance = converter;
        }
        return instance;
    }
}
