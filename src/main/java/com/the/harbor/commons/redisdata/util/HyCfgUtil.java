package com.the.harbor.commons.redisdata.util;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public class HyCfgUtil {

	/**
	 * 判断当前是否采用邀约制注册用户
	 * 
	 * @return
	 */
	public static boolean checkUserRegisterByInvite() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "user_register_type");
		return "invite".equals(value);
	}

}
