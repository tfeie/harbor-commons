package com.the.harbor.commons.redisdata.util;

import java.util.Set;

import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;

public final class HyGoUtil {

	/**
	 * 用户收藏GO
	 * 
	 * @param userId
	 * @param goId
	 */
	public static void userFavorGo(String userId, String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_GO_PREFFIX.getKey() + userId;
		cacheClient.sadd(key, goId);
		String key2 = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		cacheClient.sadd(key2, userId);
	}

	/**
	 * 用户取消收藏GO
	 * 
	 * @param userId
	 * @param goId
	 */
	public static void userCancelFavorGo(String userId, String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_GO_PREFFIX.getKey() + userId;
		cacheClient.srem(key, goId);
		String key2 = RedisDataKey.KEY_GO_FAVORITE_PREFFIX.getKey() + goId;
		cacheClient.srem(key2, userId);
	}

	/**
	 * 获取用户收藏的GO数量
	 * 
	 * @param userId
	 * @return
	 */
	public static Set<String> getUserFavorGoes(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_GO_PREFFIX.getKey() + userId;
		return cacheClient.smembers(key);
	}

	/**
	 * 获取用户收藏的GO总数
	 * 
	 * @param userId
	 * @return
	 */
	public static long getUserFavorGoesCount(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_USER_FAVOR_GO_PREFFIX.getKey() + userId;
		return cacheClient.scard(key);
	}

	/**
	 * 获取活动被多少用户收藏
	 * 
	 * @param goId
	 * @return
	 */
	public static long getGoFavoredUserCount(String goId) {
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
	 * 
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
	 * 删除活动订单的评论数据
	 * 
	 * @param goId
	 * @param orderId
	 * @param commentId
	 */
	public static void deleteGoOrderCommentId(String goId, String orderId, String commentId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_ORDER_COMMENTS_IDS_PREFFIX.getKey() + orderId;
		cacheClient.zrem(key, commentId);

		key = RedisDataKey.KEY_GO_COMMENTS_IDS_PREFFIX.getKey() + goId;
		cacheClient.zrem(key, commentId);
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

	/**
	 * 用户申请参加GROUP活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static void userApplyJoinGroup(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX.getKey() + goId;
		cacheClient.zadd(key, DateUtil.getCurrentTimeMillis(), userId);
	}

	/**
	 * 获取GROUP活动中等待审核的用户集合
	 * 
	 * @param goId
	 * @return
	 */
	public static Set<String> getGroupWaitConfirmUsers(String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX.getKey() + goId;
		return cacheClient.zrevrange(key, 0, -1);
	}

	public static Set<String> getGroupConfirmedUsers(String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_CONFIRMED_USER_PREFFIX.getKey() + goId;
		return cacheClient.zrevrange(key, 0, -1);
	}

	/**
	 * 同意用户参加GROUP活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static void agreeUserJoinGroupApply(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX.getKey() + goId;
		// 首先将用户从待确认列表删除
		cacheClient.zrem(key, userId);
		// 其次将用户加入到已经确认列表
		String key2 = RedisDataKey.KEY_GO_JOIN_CONFIRMED_USER_PREFFIX.getKey() + goId;
		cacheClient.zadd(key2, DateUtil.getCurrentTimeMillis(), userId);
	}

	/**
	 * 拒绝用户参加GROUP活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static void rejectUserJoinGroupApply(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX.getKey() + goId;
		// 首先将用户从待确认列表删除
		cacheClient.zrem(key, userId);
	}

	/**
	 * 获取GROUP活动确认参加的用户总数
	 * 
	 * @param goId
	 * @return
	 */
	public static int getGroupConfirmedJoinUsersCount(String goId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_CONFIRMED_USER_PREFFIX.getKey() + goId;
		return cacheClient.zrange(key, 0, -1).size();
	}

	/**
	 * 判断用户是否已经参加了此活动
	 * 
	 * @param goId
	 * @param userId
	 */
	public static boolean checkUserHadJointGroup(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_CONFIRMED_USER_PREFFIX.getKey() + goId;
		return cacheClient.zrange(key, 0, -1).contains(userId);
	}

	/**
	 * 判断用户是否申请了此活动
	 * 
	 * @param goId
	 * @param userId
	 * @return
	 */
	public static boolean checkUserHadAppliedGroup(String goId, String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String key = RedisDataKey.KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX.getKey() + goId;
		return cacheClient.zrange(key, 0, -1).contains(userId);
	}

}
