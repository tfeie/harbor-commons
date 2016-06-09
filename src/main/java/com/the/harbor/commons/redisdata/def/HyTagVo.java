package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyTagVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tagId;

	private String tagType;

	private String tagName;

	private String scopeType;

	private String tagCat;

	private boolean selected;

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

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getTagCat() {
		return tagCat;
	}

	public void setTagCat(String tagCat) {
		this.tagCat = tagCat;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
