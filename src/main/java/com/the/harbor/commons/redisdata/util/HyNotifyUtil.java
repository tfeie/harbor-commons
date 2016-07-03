package com.the.harbor.commons.redisdata.util;

import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyNotifyVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;

public class HyNotifyUtil {

	/**
	 * 给单个用户追加一条未读消息记录
	 * 
	 * @param userId
	 * @param notifyId
	 */
	public static void recordSingleUserNotify(String userId, String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_USER_UNREAD_IDS_PREFFIX.getKey() + userId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), notifyId);
	}

	/**
	 * 删除一条未读消息，标识此消息已经读取或忽略
	 * 
	 * @param userId
	 * @param notifyId
	 */
	public static void deleteSingleUserNotify(String userId, String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_USER_UNREAD_IDS_PREFFIX.getKey() + userId;
		cacheClient.zrem(key, notifyId);
	}

	/**
	 * 获取用户所有的未读消息，按照时间排序<br>
	 * 
	 * @param beId
	 * @param start
	 *            --0表示从第1个元素
	 * @param end
	 *            -- -1表示最后一个元素
	 * @return
	 */
	public static Set<String> getUserNotifyIds(String userId, long start, long end) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_USER_UNREAD_IDS_PREFFIX.getKey() + userId;
		return cacheClient.zrevrange(key, start, end);
	}

	/**
	 * 获取未读消息总数
	 * 
	 * @param userId
	 * @return
	 */
	public static int getUnReadNotifyCount(String userId) {
		Set<String> set = getUserNotifyIds(userId, 0, -1);
		return set.size();
	}

	/**
	 * 记录一条消息内容
	 * 
	 * @param notifyId
	 * @param data
	 */
	public static void recordNotify(String notifyId, HyNotifyVo notify) {
		if (notify == null) {
			return;
		}
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
		cacheClient.set(key, JSON.toJSONString(notify));
	}

	/**
	 * 获取一条消息的数据
	 * 
	 * @param notifyId
	 * @return
	 */
	public static HyNotifyVo getNotify(String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
		String data = cacheClient.get(key);
		if (StringUtil.isBlank(data)) {
			return null;
		}
		return JSON.parseObject(data, HyNotifyVo.class);
	}

	/**
	 * 删除一条消息数据
	 * 
	 * @param commentId
	 */
	public static void deleteNotify(String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
		cacheClient.del(key);
	}
}
