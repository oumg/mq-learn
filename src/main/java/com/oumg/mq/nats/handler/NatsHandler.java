package com.oumg.mq.nats.handler;

import io.nats.client.Message;
import io.nats.client.MessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理订阅消息
 * 
 * @author oumingfeng
 *
 */
public class NatsHandler implements MessageHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(NatsHandler.class);

	@Override
	public void onMessage(Message msg) throws InterruptedException {
		LOG.info("【NATS】收到消息：{}",new String(msg.getData()));
		
	}

}
