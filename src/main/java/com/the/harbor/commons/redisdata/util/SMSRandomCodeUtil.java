package com.the.harbor.commons.redisdata.util;

import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.util.RandomUtil;

public final class SMSRandomCodeUtil {

	private static final String PREFIX_KEY = "harbor.sms.random";

	/**
	 * 增加短信随机验证码，并设置过期时间
	 * 
	 * @param phoneNumber
	 * @param randomCode
	 */
	public static void setSmsRandomCode(String phoneNumber, String randomCode) {
		String key = PREFIX_KEY + "." + phoneNumber;
		int seconds = GlobalSettings.getSMSRandomCodeExpireSeconds();
		ICacheClient client = CacheFactory.getClient();
		client.set(key, randomCode);
		client.expire(key, seconds);
	}

	/**
	 * 获取短信验证码
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static String getSmsRandomCode(String phoneNumber) {
		String key = PREFIX_KEY + "." + phoneNumber;
		ICacheClient client = CacheFactory.getClient();
		String randomCode = client.get(key);
		return randomCode;
	}

	/**
	 * 删除短信验证码
	 * 
	 * @param phoneNumber
	 */
	public static void delSmsRandomCode(String phoneNumber) {
		String key = PREFIX_KEY + "." + phoneNumber;
		ICacheClient client = CacheFactory.getClient();
		client.del(key);
	}

	/**
	 * 生成短信验证码
	 * 
	 * @return
	 */
	public static String createRandomCode() {
		return RandomUtil.generateNumber(6);
	}
}
