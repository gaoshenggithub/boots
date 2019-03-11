package com.mq.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置:
 * 1.RabbitAutoConfiguration
 * 2.有自动配置连接工厂
 * 3.RabbitProperties:给RabbitMQ发送和接受消息的组件
 * 4.RabbitTemplate: 给RabbitMQ发送和接受消息
 * 5.AmqpAdmin: RabbitMQ系统管理组件功能
 *   AmqpAdmin:创建和删除功能Queue,Exchange
 * 6.@EnableRabbit+@RabbitListener监听消息队列的内容
 */
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}
