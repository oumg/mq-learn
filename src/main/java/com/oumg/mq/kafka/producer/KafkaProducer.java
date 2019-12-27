package com.oumg.mq.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	@Value("${spring.kafka.topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	public void sendMsg(){
		kafkaTemplate.send(topic,"hello world!");
	}
}
