package com.the.harbor.commons.redisdata.util;

import java.util.Set;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;

public final class HyGoUtil {

	/**
	 * 获取活动被多少用户收藏
	 * 
	 * @param goId
	 * @return
	 */
	public static long getGoFavoriteCount(String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		return cacheClient.scard(key);
	}

	/**
	 * 判断用户是否已经收藏了此活动
	 * 
	 * @param goId
	 * @param userId
	 * @return
	 */
	public static boolean checkUserGoFavorite(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		return cacheClient.sismember(key, userId);
	}

	/**
	 * 记录用户收藏活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static void recordUserFavorite(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		cacheClient.sadd(key, userId);
	}

	/**
	 * 用户取消收藏活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static void userCancelFavorite(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		cacheClient.srem(key, userId);
	}

	/**
	 * 记录GO发表的评论ID。<br>
	 * 采用redis的有序SortSet按照Score为时间戳排序
	 * 
	 * @param goId
	 * @param commentId
	 */
	public static void recordGoCommentId(String goId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_IDS_PREFFIX.getKey() + goId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), commentId);
	}

	/**
	 * 删除GO的评论ID内容<br>
	 * 
	 * @param goId
	 * @param commentId
	 */
	public static void deleteGoCommentId(String goId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_IDS_PREFFIX.getKey() + goId;
		cacheClient.zrem(key, commentId);
	}

	/**
	 * 获取GO发表的评论ID有序集合<br>
	 * 
	 * @param goId
	 * @param start
	 *            --0表示从第1个元素
	 * @param end
	 *            -- -1表示最后一个元素
	 * @return
	 */
	public static Set<String> getGoCommentIds(String goId, long start, long end) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_IDS_PREFFIX.getKey() + goId;
		return cacheClient.zrange(key, start, end);
	}

	/**
	 * 获取GO评论总数
	 * 
	 * @param goId
	 * @return
	 */
	public static int getGoCommentsCount(String goId) {
		Set<String> set = getGoCommentIds(goId, 0, -1);
		return set.size();
	}

	/**
	 * 记录GO ORDER发表的评论ID。<br>
	 * 采用redis的有序SortSet按照Score为时间戳排序
	 * 
	 * @param goId
	 * @param commentId
	 */
	public static void recordGoOrderCommentId(String orderId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_ORDER_COMMENTS_IDS_PREFFIX.getKey() + orderId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), commentId);
	}

	public static Set<String> getGoOrderCommentIds(String orderId, long start, long end) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_ORDER_COMMENTS_IDS_PREFFIX.getKey() + orderId;
		return cacheClient.zrange(key, start, end);
	}

	/**
	 * 记录活动与订单的评论信息
	 * @param goId
	 * @param orderId
	 * @param commentId
	 */
	public static void recordGoOrderCommentId(String goId, String orderId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_ORDER_COMMENTS_IDS_PREFFIX.getKey() + orderId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), commentId);

		key = RedisDataKey.KEY_GO_COMMENTS_IDS_PREFFIX.getKey() + goId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), commentId);
	}

	/**
	 * 记录一条GO评论内容
	 * 
	 * @param commentId
	 * @param commentsData
	 */
	public static void recordGoComment(String commentId, String commentsData) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		cacheClient.set(key, commentsData);
	}

	/**
	 * 获取一条GO评论的数据
	 * 
	 * @param commentId
	 * @return
	 */
	public static String getGoComment(String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		return cacheClient.get(key);
	}

	/**
	 * 删除一条GO评论内容
	 * 
	 * @param commentId
	 */
	public static void deleteGoComment(String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		cacheClient.del(key);
	}

}
