package com.oumg.mq.rocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RocketMqProducer {
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	public void sendMSG(Message<String> msg) {
		rocketMQTemplate.send(msg);
	}

}
