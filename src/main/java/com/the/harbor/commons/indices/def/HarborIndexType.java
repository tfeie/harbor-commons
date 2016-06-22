package com.the.harbor.commons.indices.def;

import com.the.harbor.base.enumeration.base.Behaviour;

public enum HarborIndexType implements Behaviour {

	HY_UNIVERSITY("hy_university", "海外学校数据"),

	HY_BE("hy_be", "用户发表的BE");

	private String value;

	private String desc;

	private HarborIndexType(String value, String desc) {
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
