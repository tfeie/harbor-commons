package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyCountryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String countryCode;

	private String countryName;

	private String countryRgb;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode == null ? null : countryCode.trim();
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName == null ? null : countryName.trim();
	}

	public String getCountryRgb() {
		return countryRgb;
	}

	public void setCountryRgb(String countryRgb) {
		this.countryRgb = countryRgb == null ? null : countryRgb.trim();
	}

}
