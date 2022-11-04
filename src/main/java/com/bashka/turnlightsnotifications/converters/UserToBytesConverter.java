package com.bashka.turnlightsnotifications.converters;

import com.bashka.turnlightsnotifications.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Nullable;

public class UserToBytesConverter implements Converter<User, byte[]> {
    private final Jackson2JsonRedisSerializer<User> serializer;

    public UserToBytesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(User.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public byte[] convert(@Nullable User value) {
        return serializer.serialize(value);
    }
}