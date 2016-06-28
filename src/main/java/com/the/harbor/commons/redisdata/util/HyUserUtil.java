package com.the.harbor.commons.redisdata.util;

import java.util.Set;

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

	public static void buildOpenIdAndUserIdMapped(String openId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		cacheClient.hset(RedisDataKey.KEY_WEIXIN_REG_USER.getKey(), openId, userId);
	}

	public static String getUserIdByOpenId(String openId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		return cacheClient.hget(RedisDataKey.KEY_WEIXIN_REG_USER.getKey(), openId);
	}

	/**
	 * A用户关注B用户
	 * 
	 * @param userId
	 * @param fansUserId
	 */
	public static void userAGuanzhuUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		// 记录用户A是用户B的粉丝
		String key1 = RedisDataKey.KEY_USER_FANS_GUANZHU_ME_PREFFIX.getKey() + userB;
		cacheClient.sadd(key1, userA);
		// 记录用户A所关注的用户包含B
		String key2 = RedisDataKey.KEY_USER_FANS_I_GUANZHU_PREFFIX.getKey() + userA;
		cacheClient.sadd(key2, userB);
	}

	/**
	 * A用户取消关注用户B
	 * 
	 * @param userId
	 * @param fansUserId
	 */
	public static void userACancelGuanzhuUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		// 将用户A从用户B的粉丝列表中移除
		String key1 = RedisDataKey.KEY_USER_FANS_GUANZHU_ME_PREFFIX.getKey() + userB;
		cacheClient.srem(key1, userA);
		// 用户A的关注用户列表中移除用户B
		String key2 = RedisDataKey.KEY_USER_FANS_I_GUANZHU_PREFFIX.getKey() + userA;
		cacheClient.srem(key2, userB);
	}

	/**
	 * 获取用户的粉丝列表
	 * 
	 * @param userA
	 * @return
	 */
	public static Set<String> getUserFans(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key1 = RedisDataKey.KEY_USER_FANS_GUANZHU_ME_PREFFIX.getKey() + userId;
		return cacheClient.smembers(key1);
	}

	/**
	 * 获取用户关注的人
	 * 
	 * @param userA
	 * @return
	 */
	public static Set<String> getUserGuanzhuUsers(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key1 = RedisDataKey.KEY_USER_FANS_I_GUANZHU_PREFFIX.getKey() + userId;
		return cacheClient.smembers(key1);
	}

	/**
	 * 用户A申请成为用户B的好友
	 * 
	 * @param userA
	 * @param userB
	 */
	public static void userAApplyFriendToUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key1 = RedisDataKey.KEY_USER_FRIEND_APPLY_PREFFIX.getKey() + userB;
		cacheClient.sadd(key1, userA);
	}

	/**
	 * 用户A同意用户B的好友申请
	 * 
	 * @param userA
	 * @param userB
	 */
	public static void userAAgreeApplyFriendofUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		// 从申请集合中移除用户B的申请
		String key = RedisDataKey.KEY_USER_FRIEND_APPLY_PREFFIX.getKey() + userA;
		cacheClient.srem(key, userB);
		// 加入到A的好友列表中
		String key1 = RedisDataKey.KEY_USER_FRIEND_BECOME_PREFFIX.getKey() + userA;
		cacheClient.sadd(key1, userB);
	}

	/**
	 * 用户A拒绝或忽略用户B的好友申请
	 * 
	 * @param userA
	 * @param userB
	 */
	public static void userARejectApplyFriendofUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key1 = RedisDataKey.KEY_USER_FRIEND_APPLY_PREFFIX.getKey() + userA;
		cacheClient.srem(key1, userB);
	}
	
	/**
	 * 用户A取消与用户B成为好友
	 * 
	 * @param userA
	 * @param userB
	 */
	public static void userARemoveFriendUserB(String userA, String userB) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key1 = RedisDataKey.KEY_USER_FRIEND_BECOME_PREFFIX.getKey() + userA;
		cacheClient.srem(key1, userB);
	}

	/**
	 * 获取用户的所有好友申请
	 * 
	 * @param userA
	 * @return
	 */
	public static Set<String> getUserFriendApplies(String userA) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FRIEND_APPLY_PREFFIX.getKey() + userA;
		return cacheClient.smembers(key);
	}

	/**
	 * 获取用户的所有正式好友
	 * 
	 * @param userA
	 * @return
	 */
	public static Set<String> getUserFriends(String userA) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FRIEND_BECOME_PREFFIX.getKey() + userA;
		return cacheClient.smembers(key);
	}

}
