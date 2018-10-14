package com.shekhar.spring.redisDemo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


public class RedisPublisherImpl {
	private final RedisTemplate<String, Object> template;
	private final ChannelTopic topic;
	private final AtomicLong counter = new AtomicLong(0);

	
	public RedisPublisherImpl(final RedisTemplate<String, Object> template, final ChannelTopic topic) {
		this.template = template;
		this.topic = topic;
	}

	@Scheduled(fixedDelay = 10000)
	public void publish() {
		template.convertAndSend(topic.getTopic(),
				"Message " + counter.incrementAndGet() + ", " + Thread.currentThread().getName());
	}
}
