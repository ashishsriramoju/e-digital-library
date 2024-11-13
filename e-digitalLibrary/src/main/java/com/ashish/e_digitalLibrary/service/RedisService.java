package com.ashish.e_digitalLibrary.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	private final RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	public RedisService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void addToCache(String key, Object value) {
		this.redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
	}
	
	public Object getFromCache(String name) {
		return this.redisTemplate.opsForValue().get(name);
	}

}
