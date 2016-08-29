package com.the.harbor.commons.redisdata.def;

public enum RedisDataKey {

	KEY_MNS_RECORD("harbor.mns.record", "MNS消息发送记录。此为一级key，二级key为消息类型+消息ID，值为消息记录"),

	KEY_ALL_AREA("harbor.all.area", "所有地域数据缓存，一级key为此，二级key为areaCode，值为HyAreaVo"),

	KEY_ALL_PROVINCE("harbor.all.province", "所有省级地域数据缓存，一级key为此，值为省份List<HyAreaVo>"),

	KEY_CITY("harbor.all.city", "所有地市地域数据缓存，一级key为此，二级key为省级areaCode，值为地市List<HyAreaVo>"),

	KEY_CFG("harbor.single.cfg", "单个系统配置缓存"),

	KEY_SINGLE_COUNTRY("harbor.single.country", "单个国家缓存"),

	KEY_ALL_COUNTRIES("harbor.all.countries", "所有国家以列表方式缓存"),

	KEY_SINGLE_INDUSTRY("harbor.single.industry", "单个行业缓存"),

	KEY_ALL_INDUSTRIES("harbor.all.industries", "所有行业以列表方式缓存"),

	KEY_ALL_DICTS("harbor.all.dicts", "所有字典数据缓存，一级key为此，二级key为typeCode.paramCode，值为list"),

	KEY_SINGLE_DICT("harbor.single.dict", "所有字典数据缓存，一级key为此，二级key为typeCode.paramCode.paramValue，值为对象"),

	KEY_BASE_INTEREST_TAGS("harbor.base.interest.tags", "所有的基础用户兴趣标签"),

	KEY_BASE_SKILL_TAGS("harbor.base.skill.tags", "所有的基础用户技能标签"),

	KEY_GO_TAGS("harbor.go.tags", "GO系统标签"),

	KEY_BE_TAGS("harbor.be.tags", "BE系统标签"),

	KEY_GROUP_TAGS("harbor.group.tags", "GROUP系统标签"), KEY_ONO_TAGS("harbor.ono.tags", "ONO系统标签"),

	KEY_GROUP_INDEX_PAGE_TAGS("harbor.group.indexpage.tags", "GROUP显示在首页标签"),

	KEY_ONO_INDEX_PAGE_TAGS("harbor.ono.indexpage.tags", "ONE显示在首页标签"),

	KEY_BE_INDEX_PAGE_TAGS("harbor.be.indexpage.tags", "BE显示在首页标签"),

	KEY_NOTIFY_USER_IDS_PREFFIX("harbor.notify.userid.", "记录发送给单个用户的消息通知，值为消息notifyId的列表。为sortset类型"),

	KEY_NOTIFY_CONTENT_PREFIX("harbor.notify.content.", "记录消息的内容，key为notifyId,值为xx序列化的值"),

	KEY_BE_DATA("harbor.be.beid.", "BE的数据缓存，后面加上BEID。值为BE对象"),

	KEY_BE_LIKES_PREFFIX("harbor.be.likes.beid.", "BE点赞数据缓存KEY前缀，后面加上BEID。值为用户列表"),

	KEY_BE_HAIBEI_REWARDUSER_PREFFIX("harbor.be.haibei.rewarduser.beid.", "BE打赏的用户缓存KEY前缀，后面加上BEID。值为用户列表"),

	KEY_BE_HAIBEI_REWARD_COUNT_PREFFIX("harbor.be.haibei.rewardcount.beid.", "BE被打赏的海贝数量,值为海贝数量"),

	KEY_BE_COMMENTS_IDS_PREFFIX("harbor.be.comments.beid.", "BE评论缓存KEY前缀，后面加上BEID。值为所有评论数据ID"),

	KEY_BE_COMMENTS_CONTENT_PREFFIX("harbor.be.comments.commentid.", "BE评论内容KEY前缀，后面加上commentId。值为对应的评论内容BeComment"),

	KEY_BE_FAVORITE_PREFFIX("harbor.be.favorite.beid.", "BE被搜藏的数据缓存KEY前缀，后面加上BEID。值为用户列表"),

	KEY_GO_DATA("harbor.go.goid.", "GO的数据，后面加上GOID，值为GO对象"),

	KEY_GO_COMMENTS_IDS_PREFFIX("harbor.go.comments.goid.", "GO评论缓存KEY前缀，后面加上GOID。值为所有评论数据ID"),

	KEY_GO_ORDER_COMMENTS_IDS_PREFFIX("harbor.go.order.comments.orderid.", "针对单个预约订单的评论缓存KEY前缀，后面加上ORDERID。值为所有评论数据ID"),

	KEY_GO_COMMENTS_CONTENT_PREFFIX("harbor.go.comments.commentid.", "GO评论内容KEY前缀，后面加上commentId。值为对应的评论内容GoComment"),

	KEY_GO_VIEW_PREFFIX("harbor.go.view.goid.", "GO被查看的数据缓存KEY前缀，后面加上GOID。值为访问数量"),

	KEY_GO_FAVORITE_PREFFIX("harbor.go.favorite.goid.", "GO被搜藏的数据缓存KEY前缀，后面加上GOID。值为用户列表"),

	KEY_GO_JOIN_CONFIRMED_USER_PREFFIX("harbor.go.join.confirmed.user.goid.", "GROUP活动审核通过的参加用户列表。值为用户列表"),

	KEY_GO_JOIN_WAIT_CONFIRM_USER_PREFFIX("harbor.go.join.waitconfirm.user.goid.", "GROUP活动等待审核的参加用户列表。值为用户列表"),

	KEY_USER_INFO_PREFFIX("harbor.user.userid.", "存储用户资料的KEY前缀，后面加上USERID，值为UserViwInfo的序列化对象"),

	KEY_USER_FANS_GUANZHU_ME_PREFFIX("harbor.user.fans.guanzhume.userid.", "关注我的粉丝记录。值为用户列表"),

	KEY_USER_FANS_I_GUANZHU_PREFFIX("harbor.user.fans.iguanzhu.userid.", "我关注的粉丝记录。值为用户列表"),

	KEY_USER_FRIEND_APPLY_PREFFIX("harbor.user.friend.apply.userid.", "记录正在申请成为这个用户好友的信息。值为用户列表"),

	KEY_USER_FRIEND_BECOME_PREFFIX("harbor.user.friend.become.userid.", "记录这个用户的正式好友。值为用户列表"),

	KEY_USER_FAVOR_GO_ONO_PREFFIX("harbor.user.favorgo.ono.userid.", "用户收藏的GO列表。key加上userId,值为goId"),

	KEY_USER_FAVOR_GO_GROUP_PREFFIX("harbor.user.favorgo.group.userid.", "用户收藏的GO列表。key加上userId,值为goId"),

	KEY_USER_FAVOR_BE_PREFFIX("harbor.user.favorbe.userid.", "用户收藏的GO列表。key加上userId,值为beId"),

	KEY_WEIXIN_REG_USER("harbor.weixin.register.user", "记录所有微信已经注册成用户的信息,二级key为openId,值为userId"),

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
