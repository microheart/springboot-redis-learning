package com.iknowers.learning.redis.base;

import com.iknowers.learning.redis.base.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTemplateTest {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testObjTemplate() {
        User user = new User();
        user.setId(1000L);
        user.setName("Curry");
        user.setDesc("A MVP basketball player");

        String key = "user:" + user.getId();
        redisTemplate.opsForValue().set(key, user);

        Assert.assertEquals(user.getName(), redisTemplate.opsForValue().get(key).getName());
        Assert.assertEquals(user.getDesc(), redisTemplate.opsForValue().get(key).getDesc());
    }
}
