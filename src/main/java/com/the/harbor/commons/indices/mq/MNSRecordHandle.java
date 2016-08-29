package com.the.harbor.commons.indices.mq;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;

public class MNSRecordHandle {

	private static final Logger LOG = Logger.getLogger(MNSRecordHandle.class);

	public static void sendMNSRecord(MNSRecord mns) {
		if (mns == null) {
			return;
		}
		if (StringUtil.isBlank(mns.getMqId())) {
			return;
		}
		if (StringUtil.isBlank(mns.getMqType())) {
			return;
		}
		try {
			String id = mns.getMqType() + "." + mns.getMqId();
			mns.setSendDate(DateUtil.getDateString(DateUtil.DATETIME_FORMAT));
			CacheFactory.getClient().hset(RedisDataKey.KEY_MNS_RECORD.getKey(), id, JSON.toJSONString(mns));
		} catch (Exception ex) {
			LOG.error("记录MNS消息发送信息失败", ex);
		}

	}

	public static void processMNSRecord(String mqId, String mqType, boolean result, String error) {
		if (StringUtil.isBlank(mqId)) {
			return;
		}
		if (StringUtil.isBlank(mqType)) {
			return;
		}
		try {
			String id = mqType + "." + mqId;

			String data = CacheFactory.getClient().hget(RedisDataKey.KEY_MNS_RECORD.getKey(), id);
			if (!StringUtil.isBlank(data)) {
				MNSRecord mns = JSON.parseObject(data, MNSRecord.class);
				mns.setConsumeStatus(
						result ? MNSRecord.Status.CONSUME_SUCCESS.name() : MNSRecord.Status.CONSUME_FAIL.name());
				mns.setConsumeError(error);
				mns.setConsumeDate(DateUtil.getDateString(DateUtil.DATETIME_FORMAT));
				CacheFactory.getClient().hset(RedisDataKey.KEY_MNS_RECORD.getKey(), id, JSON.toJSONString(mns));
			}
		} catch (Exception ex) {
			LOG.error("修改MNS消息发送信息失败", ex);
		}

	}
}
