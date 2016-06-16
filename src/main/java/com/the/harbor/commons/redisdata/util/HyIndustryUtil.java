package com.the.harbor.commons.redisdata.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.HyIndustryVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public final class HyIndustryUtil {

	public static HyIndustryVo getHyIndustry(String industryCode) {
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_SINGLE_INDUSTRY.getKey(), industryCode);
		return data == null ? null : JSONObject.parseObject(data, HyIndustryVo.class);
	}

	public static List<HyIndustryVo> getAllHyIndustries() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_ALL_INDUSTRIES.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyIndustryVo.class);
	}

	public static String getHyIndustryName(String industryCode) {
		HyIndustryVo b = getHyIndustry(industryCode);
		return b == null ? null : b.getIndustryName();
	}

}
