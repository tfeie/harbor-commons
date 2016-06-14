package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyDictsVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dictId;

	private String typeCode;

	private String paramCode;

	private String paramValue;

	private int disorder;

	private String paramDesc;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId == null ? null : dictId.trim();
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode == null ? null : paramCode.trim();
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue == null ? null : paramValue.trim();
	}

	public int getDisorder() {
		return disorder;
	}

	public void setDisorder(int disorder) {
		this.disorder = disorder;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc == null ? null : paramDesc.trim();
	}

}
