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

	public static List<HyTagVo> getAllGoTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_GO_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllGroupTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_GROUP_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllOnOTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_ONO_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllBeTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_BE_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllGroupIndexPageTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_GROUP_INDEX_PAGE_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllOnOIndexPageTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_ONO_INDEX_PAGE_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

	public static List<HyTagVo> getAllBeIndexPageTags() {
		String data = CacheFactory.getClient().get(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey());
		return data == null ? null : JSONArray.parseArray(data, HyTagVo.class);
	}

}
