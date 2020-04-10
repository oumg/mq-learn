package com.oumg.mq.nats.client;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Options;
import io.nats.client.impl.NatsImpl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.oumg.mq.nats.common.NatsTopic;
import com.oumg.mq.nats.listener.NatsListener;

@Component
public class NatsClient {
	
	private final Logger LOG = LoggerFactory.getLogger(NatsClient.class);
	
	@Value("${nats.pings.out}")
	private int pingsOut;
	
	@Value("${nats.send.time.out}")
	private long timeOut;
	
	@Value("${nats.connect.time.out}")
	private long connectTimeOut;
	
	@Value("${nats.ping.interval}")
	private long pingInterval;
	
	@Value("${nats.user.name}")
	private String username;
	
	@Value("${nats.user.password}")
	private String password;
	
	@Value("${nats.url}")
	private String url;
	
	private Connection connection;
	
	/**
	 * 初始化连接
	 * 
	 */
	@PostConstruct
	public void init() {
		Options options = new Options.Builder()
			.maxReconnects(-1)
			.maxPingsOut(this.pingsOut)
			.connectionTimeout(Duration.ofSeconds(this.connectTimeOut))
			.userInfo(this.username, this.password)
			.pingInterval(Duration.ofMillis(this.pingInterval))
			.connectionListener(new NatsListener())
			.server(this.url)
			.build();
		try {
			this.connection = NatsImpl.createConnection(options, true);
		} catch (IOException | InterruptedException e) {
			LOG.error("【NATS】init-error:{}",e);
		}
	}
	
	/**
	 * 添加订阅主题
	 * 
	 * @param subscribe
	 * @param handler
	 */
	public void subscribe(NatsTopic subscribe,MessageHandler handler) {
		this.connection.createDispatcher(handler).subscribe(subscribe.topic);
	}
	
	/**
	 * 发送消息，没有回复
	 * 
	 * @param natsTopic
	 * @param t
	 */
	public <T> void pulishSend(NatsTopic natsTopic,T t) {
		if(this.connection != null)
			this.connection.publish(natsTopic.topic, JSON.toJSONString(t).getBytes(Charset.defaultCharset()));
	}
	
	/**
	 * 回复消息
	 * 
	 * @param natsTopic
	 * @param replyTo
	 * @param t
	 */
	public <T> void pulishSend(NatsTopic natsTopic,String replyTo,T t) {
		if(this.connection != null)
			this.connection.publish(natsTopic.topic,replyTo, JSON.toJSONString(t).getBytes(Charset.defaultCharset()));
	}
	
	/**
	 * 发送消息，等待回复
	 * 
	 * @param natsTopic
	 * @param t
	 * @return
	 */
	public <T>  Message  request(NatsTopic natsTopic,T t) {
		if(this.connection != null)
			try {
				LOG.debug("【NATS】topic:{},send-data:{}",natsTopic.topic,JSON.toJSONString(t));
				return this.connection.request(natsTopic.topic, JSON.toJSONString(t).getBytes(Charset.defaultCharset()),Duration.ofMillis(this.timeOut));
			} catch (InterruptedException e) {
				LOG.error("【NATS】topic:{},send-data:{},happen:{}",natsTopic.topic,JSON.toJSONString(t),e);
			}
		return null;
	}
	
	/**
	 * 发送消息等待回复
	 * 
	 * @param natsTopic
	 * @param t
	 * @param timeOut
	 * @return
	 */
	public <T>  Message  request(NatsTopic natsTopic,T t,long timeOut) {
		if(this.connection != null)
			try {
				LOG.debug("【NATS】topic:{},send-data:{}",natsTopic.topic,JSON.toJSONString(t));
				return this.connection.request(natsTopic.topic, JSON.toJSONString(t).getBytes(Charset.defaultCharset()),Duration.ofMillis(timeOut));
			} catch (InterruptedException e) {
				LOG.error("【NATS】topic:{},send-data:{},happen:{}",natsTopic.topic,JSON.toJSONString(t),e);
			}
		return null;
	}

}


