package com.the.harbor.commons.indices.def;

import com.the.harbor.base.enumeration.base.Behaviour;

public enum HarborIndex implements Behaviour {

	HY_COMMON_DB("hy_common_db", "公共数据组"),

	HY_BE_DB("hy_be_db", "be数据组"),

	HY_COMMENT_DB("hy_comment_db", "用户评价组"),

	HY_GO_DB("hy_go_db", "go数据组");

	private String value;

	private String desc;

	private HarborIndex(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.value + " is " + this.desc;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

}
