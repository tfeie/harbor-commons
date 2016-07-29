package com.the.harbor.commons.redisdata.util;

import java.util.Arrays;
import java.util.List;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.StringUtil;

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

	/**
	 * 判断用户是否有审核用户证件的权限
	 * 
	 * @param userId
	 * @return
	 */
	public static boolean checkUserHasAuthCertRight(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "has_auth_right_users");
		if (StringUtil.isBlank(value)) {
			return false;
		}
		List<String> users = Arrays.asList(value.split(","));
		return users.contains(userId);
	}

}
