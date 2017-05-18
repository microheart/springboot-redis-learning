package com.iknowers.learning.redis.base;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSetAndGet() {
        stringRedisTemplate.opsForValue().set("name", "hello-redis");
        Assert.assertEquals("hello-redis", stringRedisTemplate.opsForValue().get("name"));
    }
}
