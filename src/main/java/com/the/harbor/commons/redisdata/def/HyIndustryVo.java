package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyIndustryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String industryCode;

	private String industryName;

	private int sortId;

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

}
