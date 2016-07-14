package com.the.harbor.test;

import java.util.Set;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;

import redis.clients.jedis.SortingParams;

public class RedisTest {

	public static void main(String[] args) {
		ICacheClient client = CacheFactory.getClient();
		for (int i = 1; i < 20; i++) {
			//client.zadd("zc", i, "zc"+i);
		}

		

		int pageNo =1;
		int pageSize=5;
		int start = (pageNo- 1) * pageSize;
		int end =pageNo * pageSize-1;
		Set<String> ls=client.zrevrange("zc", start, end);
		for(String key:ls){
			System.out.println(key);
		}

	}

}
