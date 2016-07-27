package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyAreaVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String areaCode;

	private String areaName;

	private String areaLevel;

	private String parentAreaCode;

	private int sort;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode == null ? null : areaCode.trim();
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel == null ? null : areaLevel.trim();
	}

	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode == null ? null : parentAreaCode.trim();
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
