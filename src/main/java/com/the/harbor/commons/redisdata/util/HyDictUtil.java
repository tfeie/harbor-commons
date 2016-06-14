package com.the.harbor.commons.redisdata.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.HyDictsVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public final class HyDictUtil {

	public static final String SPLIT = ".";

	public static List<HyDictsVo> getHyDicts(String typeCode, String paramCode) {
		String key = typeCode + SPLIT + paramCode;
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_ALL_DICTS.getKey(), key);
		return data == null ? null : JSON.parseArray(data, HyDictsVo.class);
	}

	public static List<HyDictsVo> getHyDicts(String key) {
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_ALL_DICTS.getKey(), key);
		return data == null ? null : JSON.parseArray(data, HyDictsVo.class);
	}

	public static HyDictsVo getHyDict(String typeCode, String paramCode, String paramValue) {
		String key = typeCode + SPLIT + paramCode + SPLIT + paramValue;
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_SINGLE_DICT.getKey(), key);
		return data == null ? null : JSON.parseObject(data, HyDictsVo.class);
	}

	public static String getHyDictDesc(String typeCode, String paramCode, String paramValue) {
		HyDictsVo o = getHyDict(typeCode, paramCode, paramValue);
		return o == null ? null : o.getParamDesc();
	}
}
