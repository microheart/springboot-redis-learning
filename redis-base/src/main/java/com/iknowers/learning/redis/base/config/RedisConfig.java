package com.iknowers.learning.redis.base.config;

import com.iknowers.learning.redis.base.model.User;
import com.iknowers.learning.redis.base.util.RedisObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 通过不同的profile 验证不同的序列化机制
 */
@Configuration
public class RedisConfig {

/*    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("192.168.207.128");
        return connectionFactory;
    }*/

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Profile("dev")
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());

        return template;
    }

    @Bean
    @Profile("prod")
    public RedisTemplate<String, User> redisTemplateByJson(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        //template.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    @Profile("test")
    public RedisTemplate<String, User> redisTemplateByOxm(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new OxmSerializer());

        return template;
    }
}
