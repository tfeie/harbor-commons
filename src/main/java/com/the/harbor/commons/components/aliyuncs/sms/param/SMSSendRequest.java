package com.the.harbor.commons.components.aliyuncs.sms.param;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class SMSSendRequest {

	public enum SMSType {
		NORMARL;
	}

	// 公共回传参数
	private String extend;

	// 短信类型
	private String smsType;

	// 短信签名
	private String smsFreeSignName;

	// 短信参数变量
	private JSONObject smsParams;

	// 接收手机号码
	private List<String> recNumbers;

	// 短信模板编码
	private String smsTemplateCode;

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}

	public void setSmsFreeSignName(String smsFreeSignName) {
		this.smsFreeSignName = smsFreeSignName;
	}

	public JSONObject getSmsParams() {
		return smsParams;
	}

	public void setSmsParams(JSONObject smsParams) {
		this.smsParams = smsParams;
	}

	public List<String> getRecNumbers() {
		return recNumbers;
	}

	public void setRecNumbers(List<String> recNumbers) {
		this.recNumbers = recNumbers;
	}

	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}

	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}

}
