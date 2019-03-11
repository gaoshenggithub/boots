package com.mq.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.mq.rabbitmq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitMq")
public class MQController {
	//@Autowired
	//private MQService mqService;

	@RequestMapping("getMQ.do")
	public JSONObject getMQ(@RequestParam JSONObject params) {
		JSONObject result = new JSONObject();

		result.put("code", 200);
		result.put("status", "SUCCESS");
		return result;
	}
}
