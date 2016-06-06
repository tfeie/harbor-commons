package com.the.harbor.commons.redisdata.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.HyTagVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public class HyTagUtil {

	public static List<HyTagVo> getAllBaseSkillTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_BASE_SKILL_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllBaseInterestTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_BASE_INTEREST_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

}
