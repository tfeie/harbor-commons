package com.the.harbor.commons.redisdata.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.redisdata.def.HyCountryVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;

public final class HyCountryUtil {

	public static HyCountryVo getHyCountry(String countryCode) {
		String data = CacheFactory.getClient().hget(RedisDataKey.KEY_SINGLE_COUNTRY.getKey(), countryCode);
		return data == null ? null : JSONObject.parseObject(data, HyCountryVo.class);
	}

	public static List<HyCountryVo> getAllHyCountries() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_ALL_COUNTRIES.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyCountryVo.class);
	}

}
