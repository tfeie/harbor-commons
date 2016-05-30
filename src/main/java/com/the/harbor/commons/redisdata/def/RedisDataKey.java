package com.the.harbor.commons.redisdata.def;

public enum RedisDataKey {

	KEY_SINGLE_COUNTRY("harbor.single.country", "单个国家缓存"),

	KEY_ALL_COUNTRIES("harbor.all.countries", "所有国家以列表方式缓存");
	;

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
