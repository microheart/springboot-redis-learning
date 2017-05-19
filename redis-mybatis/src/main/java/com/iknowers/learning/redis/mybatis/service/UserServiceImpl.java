package com.iknowers.learning.redis.mybatis.service;

import com.iknowers.learning.redis.mybatis.dao.UserMapper;
import com.iknowers.learning.redis.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    User selectById( Long id) {
        if (id == null) {
            throw new NullPointerException("null id");
        }

        ValueOperations<String, User> userOperations = redisTemplate.opsForValue();

        String key = key(id);
        if (redisTemplate.hasKey(key)) {
            User user = userOperations.get(key);
            logger.info("[Cache] load user {} by {} from cache.", user, key);

            return user;
        }

        User user = userMapper.selectById(id);

        if (user != null) {
            userOperations.set(key, user);
            logger.info("[Cache] store user {} -> {} to cache", key, user);
        }

        return user;
    }

    void insert(User user) {
        logger.info("insert user {} to DB.", user);
        userMapper.insert(user);
    }

    List<User> selectAll() {
        return userMapper.selectAll();
    }

    void update(User user) {
        userMapper.update(user);

        String key = key(user.getId());
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("[Cache] delete key {} from cache because user {} updated.", key, user);
        }
    }

    void deleteById(Long id) {
        userMapper.deleteById(id);

        String key = key(id);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("[Cache] delete key {} from cache because user key {} deleted.", key, key);
        }
    }

    public String key(Long id) {
        return "user:" + id;
    }
}
