package com.shekhar.spring.redisDemo;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisListService {

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	public void addLink(String key, String value) {
		listOps.leftPush(key, value);
	}

	public Long getSize(String userId) {
		return listOps.size(userId);
	}
}
