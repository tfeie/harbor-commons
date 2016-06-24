package com.the.harbor.commons.redisdata.util;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public final class HyBeUtil {

	/**
	 * 获取BE的点赞总数
	 * 
	 * @param beId
	 * @return
	 */
	public static long getBeDianzanCount(String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_LIKES_PREFFIX.getKey() + beId;
		return cacheClient.scard(key);
	}

	/**
	 * 判断用户是否点赞
	 * 
	 * @param beId
	 * @param userId
	 * @return
	 */
	public static boolean checkUserDianzan(String beId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_LIKES_PREFFIX.getKey() + beId;
		return cacheClient.sismember(key, userId);
	}

	/**
	 * 记录用户点赞
	 * 
	 * @param beId
	 * @param userId
	 */
	public static void recordUserDianzan(String beId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_LIKES_PREFFIX.getKey() + beId;
		cacheClient.sadd(key, userId);
	}

}
