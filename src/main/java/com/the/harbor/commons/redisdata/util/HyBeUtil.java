package com.the.harbor.commons.redisdata.util;

import java.util.Set;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;

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

	/**
	 * 用户取消点赞
	 * 
	 * @param beId
	 * @param userId
	 */
	public static void userCancelZan(String beId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_LIKES_PREFFIX.getKey() + beId;
		cacheClient.srem(key, userId);
	}

	/**
	 * 返回所有点赞的用户集合
	 * 
	 * @param beId
	 * @return
	 */
	public static Set<String> getDianzanUsers(String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_LIKES_PREFFIX.getKey() + beId;
		return cacheClient.smembers(key);
	}

	/**
	 * 记录BE发表的评论ID。<br>
	 * 采用redis的有序SortSet按照Score为时间戳排序
	 * 
	 * @param beId
	 * @param commentId
	 */
	public static void recordBeCommentId(String beId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_IDS_PREFFIX.getKey() + beId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), commentId);
	}

	/**
	 * 删除BE发表的评论ID内容<br>
	 * 
	 * @param beId
	 * @param commentId
	 */
	public static void deleteBeCommentId(String beId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_IDS_PREFFIX.getKey() + beId;
		cacheClient.zrem(key, commentId);
	}

	/**
	 * 获取BE发表的评论ID有序集合<br>
	 * 
	 * @param beId
	 * @param start
	 *            --0表示从第1个元素
	 * @param end
	 *            -- -1表示最后一个元素
	 * @return
	 */
	public static Set<String> getBeCommentIds(String beId, long start, long end) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_IDS_PREFFIX.getKey() + beId;
		return cacheClient.zrange(key, start, end);
	}

	/**
	 * 获取BE评论总数
	 * 
	 * @param beId
	 * @return
	 */
	public static int getBeCommentsCount(String beId) {
		Set<String> set = getBeCommentIds(beId, 0, -1);
		return set.size();
	}

	/**
	 * 记录一条BE评论内容
	 * 
	 * @param commentId
	 * @param commentsData
	 */
	public static void recordBeComment(String commentId, String commentsData) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		cacheClient.set(key, commentsData);
	}

	/**
	 * 删除一条BE评论内容
	 * 
	 * @param commentId
	 */
	public static void deleteBeComment(String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		cacheClient.del(key);
	}

}
