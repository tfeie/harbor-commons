package com.the.harbor.commons.redisdata.util;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public final class HyUserUtil {

	public static void storeUserInfo2Redis(String userId, String userData) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_INFO_PREFFIX.getKey() + userId;
		cacheClient.set(key, userData);
	}

	public static String getUserInfoFromRedis(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_INFO_PREFFIX.getKey() + userId;
		return cacheClient.get(key);
	}

}
