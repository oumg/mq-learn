package com.oumg.mq.nats.common;

public enum NatsTopic {
	
	;
	
	public String index;
	public String topic;
	
	private NatsTopic(String index, String topic) {
		this.index = index;
		this.topic = topic;
	}
	
	

}
