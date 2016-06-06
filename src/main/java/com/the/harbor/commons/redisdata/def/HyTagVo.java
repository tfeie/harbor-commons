package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyTagVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tagId;

	private String tagType;

	private String tagName;

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
