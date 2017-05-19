package com.iknowers.learning.redis.mybatis.service;

import com.iknowers.learning.redis.mybatis.dao.UserMapperTest;
import com.iknowers.learning.redis.mybatis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("Curry");
        user.setAge(28);
        user.setCity("USA Oakland");
        userService.insert(user);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));
    }

    @Test
    public void testSelect() {
        User user = new User();
        user.setName("Thompson");
        user.setAge(27);
        user.setCity("USA Oakland");
        userService.insert(user);
        logger.info(user.toString());

        Assert.assertTrue(user.getId() > 0);
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));

        User userFromDB = userService.selectById(user.getId());
        Assert.assertEquals(user.getCity(), userFromDB.getCity());
        Assert.assertTrue(redisTemplate.hasKey(userService.key(user.getId())));
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("Kevin Durant");
        user.setAge(27);
        user.setCity("USA Oklahoma");
        userService.insert(user);
        logger.info(user.toString());

        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));

        // load to cache
        logger.info(userService.selectById(user.getId()).toString());
        Assert.assertTrue(redisTemplate.hasKey(userService.key(user.getId())));

        // delete from cache
        user.setCity("USA Oakland");
        userService.update(user);
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));

        // load to cache
        Assert.assertEquals("USA Oakland", userService.selectById(user.getId()).getCity());
        Assert.assertTrue(redisTemplate.hasKey(userService.key(user.getId())));
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("JaVale McGee");
        user.setAge(29);
        user.setCity("USA Oakland");
        userService.insert(user);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));


        // load to cache
        logger.info(userService.selectById(user.getId()).toString());
        Assert.assertTrue(redisTemplate.hasKey(userService.key(user.getId())));

        // delete from cache
        userService.deleteById(user.getId());
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));

        // no cache
        Assert.assertNull(userService.selectById(user.getId()));
        Assert.assertFalse(redisTemplate.hasKey(userService.key(user.getId())));
    }
}
