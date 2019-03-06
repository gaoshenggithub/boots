package com.fast.redis.config;

import com.alibaba.fastjson.JSONObject;
import com.fast.redis.config.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/getRedis")
    public JSONObject getRedis(@RequestParam Map<String,Object> map) {
        JSONObject result = new JSONObject();
        Map<String, Object> data = redisService.getUserData(map);
        result.put("code", 200);
        result.put("data", data);
        result.put("message", "FAIL");
        return result;
    }

    @RequestMapping("/updateUserData")
    public JSONObject updateUserData(@RequestParam Map<String,Object> map){
        System.out.println(map);
        JSONObject result = new JSONObject();
        Map<String, Object> i= redisService.updateUserData(map);
        result.put("code",200);
        result.put("data","");
        result.put("code",i!=null?"SUCCESS":"FAIL");
        return result;
    }

    @RequestMapping("/deleteUserData")
    public JSONObject deleteUserData(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        JSONObject result = new JSONObject();
        int i = redisService.deleteUserData(map);
        result.put("code", 200);
        result.put("data", "");
        result.put("code", i != 0 ? "SUCCESS" : "FAIL");
        return result;
    }
}
