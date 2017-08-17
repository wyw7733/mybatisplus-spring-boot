package com.baomidou.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.springboot.entity.RedisModel;
import com.baomidou.springboot.service.IRedisService;
@Service
public class RedisServiceImpl extends IRedisService<RedisModel> {
	
	private static final String REDIS_KEY = "TEST_REDIS_KEY";
	@Override
	protected String getRedisKey() {
		return this.REDIS_KEY;
	}

}
