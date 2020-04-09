package com.oumg.mq.rocketmq.consumer;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic="",consumerGroup="")
public class RocketMqListener implements RocketMQListener<MessageExt>{
	
	private final Logger LOG = LoggerFactory.getLogger(RocketMqListener.class);

	@Override
	public void onMessage(MessageExt message) {
		LOG.info("【ROCKET-MQ】{}",message.getMsgId());
	}

}
