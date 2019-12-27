package com.oumg.mq.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {


	private Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = {"${spring.kafka.topic}"})
	public void onMessage(String msg) {
		LOG.info("【kafka-listener】收到消息:{}",msg);
	}
}
