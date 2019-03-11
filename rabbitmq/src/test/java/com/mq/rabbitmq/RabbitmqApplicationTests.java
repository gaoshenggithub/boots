package com.mq.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private AmqpAdmin amqpAdmin;

	/**
	 * 单播..点对点发送
	 */
	//发送数据
	@Test
	public void contextLoads() {
		/**
		 * exchange===>路由名称
		 * routeKey===>消息队列名称
		 * message===>消息内容
		 */
		//rabbitTemplate.send(exchange,routeKey,message);
		//object默认当成消息体,只需传入要发送的对象,自动序列化发送给rabbitMq
		//rabbitTemplate.convertAndSend(exchange,routeKey,object);
		//对象被默认序列化以后发送出去
		rabbitTemplate.convertAndSend("gaosheng.direct",
				"gaosheng.test", "SpringBoot发送的测试数据......");
	}

	//接收数据
	@Test
	public void getConver() {
		Object o = rabbitTemplate.receiveAndConvert("gaosheng.test");
		System.out.println(o.toString());
	}

	@Test
	public void deleteExChange(){
		amqpAdmin.deleteExchange("gaosheng.numbers");
		amqpAdmin.deleteExchange("gaosheng.news");
	}
}
