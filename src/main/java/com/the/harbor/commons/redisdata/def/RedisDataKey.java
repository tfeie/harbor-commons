package com.the.harbor.commons.redisdata.def;

public enum RedisDataKey {

	KEY_SINGLE_COUNTRY("harbor.single.country", "单个国家缓存"),

	KEY_ALL_COUNTRIES("harbor.all.countries", "所有国家以列表方式缓存"),

	KEY_SINGLE_INDUSTRY("harbor.single.industry", "单个行业缓存"),

	KEY_ALL_INDUSTRIES("harbor.all.industries", "所有行业以列表方式缓存"),

	KEY_ALL_DICTS("harbor.all.dicts", "所有字典数据缓存，一级key为此，二级key为typeCode.paramCode，值为list"),

	KEY_SINGLE_DICT("harbor.single.dict", "所有字典数据缓存，一级key为此，二级key为typeCode.paramCode.paramValue，值为对象"),

	KEY_BASE_INTEREST_TAGS("harbor.base.interest.tags", "所有的基础用户兴趣标签"),

	KEY_BASE_SKILL_TAGS("harbor.base.skill.tags", "所有的基础用户技能标签"),

	KEY_WEIXIN_REG_USER("harbor.weixin.register.user", "记录所有微信已经注册成用户的信息,二级key为openId,值为UserInfo"),

	KEY_WEIXIN_COMMON_TOKEN("harbor.weixin.common.token", "微信公众号基础token存储,一般2小时候失效"),

	KEY_WEIXIN_TICKET("harbor.weixin.ticket", "微信公众号ticket存储,一般2小时候失效");

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
