package com.the.harbor.commons.redisdata.util;

import java.util.Set;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;

public final class HyBeUtil {

	/**
	 * 用户收藏BE
	 * 
	 * @param userId
	 * @param beId
	 */
	public static void userFavorBe(String userId, String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_BE_PREFFIX.getKey() + userId;
		cacheClient.sadd(key, beId);

		String key2 = RedisDataKey.KEY_BE_FAVORITE_PREFFIX.getKey() + beId;
		cacheClient.sadd(key2, userId);
	}

	/**
	 * 用户取消收藏BE
	 * 
	 * @param userId
	 * @param beId
	 */
	public static void userCancelFavorBe(String userId, String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_BE_PREFFIX.getKey() + userId;
		cacheClient.srem(key, beId);
		String key2 = RedisDataKey.KEY_BE_FAVORITE_PREFFIX.getKey() + beId;
		cacheClient.srem(key2, userId);
	}

	/**
	 * 获取用户收藏的BE数量
	 * 
	 * @param userId
	 * @return
	 */
	public static Set<String> getUserFavorBes(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_BE_PREFFIX.getKey() + userId;
		return cacheClient.smembers(key);
	}

	/**
	 * 获取用户收藏的BE总数
	 * 
	 * @param userId
	 * @return
	 */
	public static long getUserFavorBesCount(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_BE_PREFFIX.getKey() + userId;
		return cacheClient.scard(key);
	}

	/**
	 * 获取BE被收藏的用户总数
	 * 
	 * @param beId
	 * @return
	 */
	public static long getBeFavoredUserCount(String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_FAVORITE_PREFFIX.getKey() + beId;
		return cacheClient.scard(key);
	}

	/**
	 * 判断用户是否已经收藏了此BE
	 * 
	 * @param goId
	 * @param userId
	 * @return
	 */
	public static boolean checkUserBeFavorite(String beId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_FAVORITE_PREFFIX.getKey() + beId;
		return cacheClient.sismember(key, userId);
	}

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
	 * 用户打赏海贝记录
	 * 
	 * @param beId
	 * @param userId
	 */
	public static void userRewardBe(String beId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_HAIBEI_REWARDUSER_PREFFIX.getKey() + beId;
		cacheClient.sadd(key, userId);
	}

	/**
	 * 获取打赏用户列表
	 * 
	 * @param beId
	 * @return
	 */
	public static Set<String> getBeRewardUsers(String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_HAIBEI_REWARDUSER_PREFFIX.getKey() + beId;
		return cacheClient.smembers(key);
	}

	/**
	 * 获取打算用户数量
	 * 
	 * @param beId
	 * @return
	 */
	public static long getRewardUserCount(String beId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_HAIBEI_REWARDUSER_PREFFIX.getKey() + beId;
		return cacheClient.scard(key);
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
	 * 获取一条评论的数据
	 * 
	 * @param commentId
	 * @return
	 */
	public static String getBeComment(String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_BE_COMMENTS_CONTENT_PREFFIX.getKey() + commentId;
		return cacheClient.get(key);
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
