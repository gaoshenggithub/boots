package com.fast.redis.config.service.imple;

import com.fast.redis.config.mapper.RedisMapper;
import com.fast.redis.config.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImple implements RedisService {
    @Autowired
    private RedisMapper redisMapper;

    @Override
    @Cacheable(cacheNames = "user", key = "#map['id'].toString()")
    public Map<String, Object> getUserData(Map<String,Object> map) {
        Map<String, Object> result = redisMapper.getUserData(map);
        return result;
    }

    @Override
    @CachePut(cacheNames = "user", key = "#result['id'].toString()")
    public Map<String, Object> updateUserData(Map<String, Object> map) {
        int i = redisMapper.updateUserData(map);
        Map<String, Object> result = redisMapper.getUserData(map);
        return result;
    }

    @Override
    public int deleteUserData(Map<String, Object> map) {
        return redisMapper.deleteUserData(map);
    }

    @Override
    public List<Map<String, Object>> finUsersData() {
        return redisMapper.finUsersData();
    }
}
