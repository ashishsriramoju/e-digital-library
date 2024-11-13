package com.ashish.e_digitalLibrary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfiguration {
	
	
	@Bean
	public RedisConnectionFactory lettuceConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		
		RedisTemplate<String, Object> rt = new RedisTemplate<>();
		
		rt.setConnectionFactory(this.lettuceConnectionFactory());
		rt.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return rt;
	}

}
