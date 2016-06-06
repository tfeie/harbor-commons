package com.the.harbor.commons.redisdata.def;

public enum RedisDataKey {

	KEY_SINGLE_COUNTRY("harbor.single.country", "单个国家缓存"),

	KEY_ALL_COUNTRIES("harbor.all.countries", "所有国家以列表方式缓存"),

	KEY_SINGLE_INDUSTRY("harbor.single.industry", "单个行业缓存"),

	KEY_ALL_INDUSTRIES("harbor.all.industries", "所有行业以列表方式缓存"),

	KEY_BASE_INTEREST_TAGS("harbor.base.interest.tags", "所有的基础兴趣标签"),

	KEY_BASE_SKILL_TAGS("harbor.base.skill.tags", "所有的基础技能标签");

	private RedisDataKey(String key, String keyDesc) {
		this.key = key;
		this.keyDesc = keyDesc;
	}

	private String key;

	private String keyDesc;

	public String getKey() {
		return key;
	}

	public String getKeyDesc() {
		return keyDesc;
	}

}
