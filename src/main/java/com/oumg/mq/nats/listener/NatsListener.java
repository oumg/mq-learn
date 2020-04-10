package com.oumg.mq.nats.listener;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *nats 事件监听
 * 
 * @author oumingfeng
 *
 */
public class NatsListener implements ConnectionListener{
	
	private final Logger LOG = LoggerFactory.getLogger(NatsListener.class);

	@Override
	public void connectionEvent(Connection conn, Events type) {
		LOG.info("【NATS】事件监听：{}",type.name());
		
	}

}
