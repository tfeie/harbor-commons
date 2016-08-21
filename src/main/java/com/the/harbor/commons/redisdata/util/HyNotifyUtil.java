package com.the.harbor.commons.redisdata.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.the.harbor.base.enumeration.hynotify.Status;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyNotifyVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;

public class HyNotifyUtil {

	/**
	 * 给用户增加一条新的消息
	 * 
	 * @param notify
	 */
	public static void addUserNotify(HyNotifyVo notify) {
		if (notify == null) {
			return;
		}
		notify.setStatus(Status.UNREAD.getValue());
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notify.getNotifyId();
		cacheClient.set(key, JSON.toJSONString(notify));

		String key2 = RedisDataKey.KEY_NOTIFY_USER_IDS_PREFFIX.getKey() + notify.getAccepterId();
		cacheClient.zadd(key2, DateUtil.getCurrentTimeMillis(), notify.getNotifyId());
	}

	/**
	 * 删除消息,标记消息为删除状态
	 * 
	 * @param notifyId
	 */
	public static void deleteNotify(String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
		String data = cacheClient.get(key);
		if (StringUtil.isBlank(data)) {
			return;
		}
		HyNotifyVo notify = JSON.parseObject(data, HyNotifyVo.class);
		notify.setStatus(Status.DELETE.getValue());
		cacheClient.set(key, JSON.toJSONString(notify));
	}

	/**
	 * 读消息,标记消息为已读状态
	 * 
	 * @param notifyId
	 */
	public static void readNotify(String notifyId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
		String data = cacheClient.get(key);
		if (StringUtil.isBlank(data)) {
			return;
		}
		HyNotifyVo notify = JSON.parseObject(data, HyNotifyVo.class);
		notify.setStatus(Status.READ.getValue());
		cacheClient.set(key, JSON.toJSONString(notify));
	}

	/**
	 * 获取未读消息列表
	 * 
	 * @param userId
	 * @return
	 */
	public static List<HyNotifyVo> getUnreadNotifies(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_USER_IDS_PREFFIX.getKey() + userId;
		Set<String> ids = cacheClient.zrevrange(key, 0, -1);
		List<HyNotifyVo> l = new ArrayList<HyNotifyVo>();
		for (String notifyId : ids) {
			String key1 = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
			String data = cacheClient.get(key1);
			if (StringUtil.isBlank(data)) {
				continue;
			}
			HyNotifyVo notify = JSON.parseObject(data, HyNotifyVo.class);
			if (Status.UNREAD.getValue().equals(notify.getStatus())) {
				l.add(notify);
			}
		}
		return l;
	}

	/**
	 * 获取已读消息列表
	 * 
	 * @param userId
	 * @return
	 */
	public static List<HyNotifyVo> getreadNotifies(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_NOTIFY_USER_IDS_PREFFIX.getKey() + userId;
		Set<String> ids = cacheClient.zrevrange(key, 0, -1);
		List<HyNotifyVo> l = new ArrayList<HyNotifyVo>();
		for (String notifyId : ids) {
			String key1 = RedisDataKey.KEY_NOTIFY_CONTENT_PREFIX.getKey() + notifyId;
			String data = cacheClient.get(key1);
			if (StringUtil.isBlank(data)) {
				continue;
			}
			HyNotifyVo notify = JSON.parseObject(data, HyNotifyVo.class);
			if (Status.READ.getValue().equals(notify.getStatus())) {
				l.add(notify);
			}
		}
		return l;
	}

}
