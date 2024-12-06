package com.branch.service;


import com.branch.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for interacting with Redis for storing and retrieving user information.
 *
 * This service provides methods to save and retrieve user information to/from Redis cache.
 * It uses Jackson's `ObjectMapper` to convert `UserInfo` objects into JSON strings for Redis storage
 * and back to `UserInfo` objects when retrieving from Redis.
 */

@Service
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
    }

    public void save(String key, UserInfo userInfo) {
        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(userInfo));
        } catch (Exception ex) {
            logger.error("Error saving user to redis for key '{}': {}", key, ex.getMessage());
            throw new RuntimeException("An error occurred while saving data to Redis", ex);
        }
    }

    public UserInfo get(String key) {
        String json = redisTemplate.opsForValue().get(key);
        try {
            if (json == null) {
                return null;
            }
            return mapper.readValue(json, UserInfo.class);
        } catch (Exception ex) {
            logger.error("Error retrieving user from redis for key '{}': {}", key, ex.getMessage());
            throw new RuntimeException("An error occurred while retrieving data from Redis", ex);
        }
    }
}