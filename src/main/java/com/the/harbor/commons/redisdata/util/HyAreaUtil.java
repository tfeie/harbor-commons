package com.the.harbor.commons.redisdata.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.HyAreaVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public class HyAreaUtil {

	public static HyAreaVo getHyCountry(String areaCode) {
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_ALL_AREA.getKey(), areaCode);
		return data == null ? null : JSONObject.parseObject(data, HyAreaVo.class);
	}

	public static String getAreaName(String areaCode) {
		HyAreaVo b = getHyCountry(areaCode);
		return b == null ? null : b.getAreaName();
	}

	public static List<HyAreaVo> getProvincies() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_ALL_PROVINCE.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyAreaVo.class);
	}

	public static List<HyAreaVo> getCities(String proviceCode) {
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_CITY.getKey(), proviceCode);
		return data == null ? null : JSONArray.parseArray(data, HyAreaVo.class);
	}

}
