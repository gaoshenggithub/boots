package com.mq.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

@Component
public class MQService {

	@RabbitListener(queues = "gaosheng.test")
	public void  getListener(String str){
		System.out.println(str);
	}
}
