package com.iknowers.learning.redis.mybatis.dao;

import com.iknowers.learning.redis.mybatis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("Curry");
        user.setAge(28);
        user.setCity("USA Oakland");
        userMapper.insert(user);

        logger.info(user.toString());
        Assert.assertTrue(user.getId() > 0);
    }

    @Test
    public void testSelect() {
        User user = new User();
        user.setName("Thompson");
        user.setAge(27);
        user.setCity("USA Oakland");
        userMapper.insert(user);

        Assert.assertTrue(user.getId() > 0);

        User userFromDB = userMapper.selectById(user.getId());
        Assert.assertEquals(user.getCity(), userFromDB.getCity());
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("Kevin Durant");
        user.setAge(27);
        user.setCity("USA Oklahoma");
        userMapper.insert(user);

        user.setCity("USA Oakland");
        userMapper.update(user);

        Assert.assertEquals("USA Oakland", userMapper.selectById(user.getId()).getCity());

    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("JaVale McGee");
        user.setAge(29);
        user.setCity("USA Oakland");
        userMapper.insert(user);

        Assert.assertTrue(user.getId() > 0);

        userMapper.deleteById(user.getId());

        Assert.assertNull(userMapper.selectById(user.getId()));
    }

}
