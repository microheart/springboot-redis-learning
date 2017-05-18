package com.iknowers.learning.redis.base.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 自定义序列化
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    private static final byte[] BYTE_EMPTY_ARR = new byte[0];

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        if (obj == null) {
            return BYTE_EMPTY_ARR;
        }

        return serializer.convert(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        return deserializer.convert(bytes);
    }
}
