package com.the.harbor.test;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;

public class RedisTest {

	public static void main(String[] args) {
		ICacheClient client = CacheFactory.getClient();
		client.set("hello", "你好");
		System.out.println(client.get("hello"));

	}

}
