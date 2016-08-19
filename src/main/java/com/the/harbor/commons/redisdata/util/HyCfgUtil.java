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

	/**
	 * 判断是否超级用户
	 * 
	 * @param userId
	 * @return
	 */
	public static boolean checkSuperUser(String userId) {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "super_users");
		if (StringUtil.isBlank(value)) {
			return false;
		}
		List<String> users = Arrays.asList(value.split(","));
		return users.contains(userId);
	}
	
	/**
	 * ONO活动申请成功后通知海牛的短信模板编码
	 * @return
	 */
	public static String getSMSCodeOfOnOApplied() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_applied");
		return value;
	}
	
	/**
	 * ONO活动审核结果通知小白
	 * @return
	 */
	public static String getSMSCodeOfOnOAuditResult() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_audit_result");
		return value;
	}

	/**
	 * ONO活动海牛设置了见面时间通知小白
	 * @return
	 */
	public static String getSMSCodeOfOnOHNSetMeetingInfo() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_hn_set_meeting");
		return value;
	}
	
	/**
	 * ONO活动小白选择约见时间信息
	 * @return
	 */
	public static String getSMSCodeOfOnOXBChooseMeetingInfo() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_xb_choose_meeting");
		return value;
	}
	
	
	
	/**
	 * ONO活动小白评论后通知海牛
	 * @return
	 */
	public static String getSMSCodeOfOnOXBComment() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_xb_comment");
		return value;
	}
	
	/**
	 * ONO活动海牛确认服务结束后通知小白
	 * @return
	 */
	public static String getSMSCodeOfOnOHNEnd() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_ono_hn_end");
		return value;
	}
	
	
	
	/**
	 * 新用户报名参加GROUP通知发起者
	 * @return
	 */
	public static String getSMSCodeOfGroupUserJoin() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_group_user_join");
		return value;
	}
	
	/**
	 * 参与者评价GROUP后通知发起者
	 * @return
	 */
	public static String getSMSCodeOfGroupUserComments() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_group_user_comment");
		return value;
	}

	/**
	 * 活动结束3小时后提醒参与者去点评活动
	 * @return
	 */
	public static String getSMSCodeOfGroupRemindUsertoComments() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_group_remind_user_comment");
		return value;
	}
	
	/**
	 * GROUP活动审核结果通知参与者
	 * @return
	 */
	public static String getSMSCodeOfGroupAuditResult() {
		ICacheClient cacheClient = CacheFactory.getClient();
		String value = cacheClient.hget(RedisDataKey.KEY_CFG.getKey(), "sms_code_group_audit_result");
		return value;
	}
	
}
