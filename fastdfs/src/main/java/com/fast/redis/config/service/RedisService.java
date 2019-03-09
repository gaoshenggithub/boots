package com.fast.redis.config.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    Map<String, Object> getUserData(Map<String,Object> map);

    Map<String, Object> updateUserData(Map<String, Object> map);

    int deleteUserData(Map<String,Object> map);

    List<Map<String,Object>> finUsersData();
}
