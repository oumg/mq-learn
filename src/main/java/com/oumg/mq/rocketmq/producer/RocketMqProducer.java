package com.oumg.mq.rocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class RocketMqProducer {
	
	private final Logger LOG = LoggerFactory.getLogger(RocketMqProducer.class);
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	@Value("${rocketmq.topic}")
	private String topic;
	
	public void sendMSG() {
		Message<String> msg = MessageBuilder.withPayload("hello world,to send msg oumg!")
				.build();
		LOG.info("【ROCKET-MQ】发送：{}",JSON.toJSONString(msg));
		rocketMQTemplate.syncSend(topic, msg);
	}

}
