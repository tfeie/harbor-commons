package com.the.harbor.commons.web.model;

import java.io.Serializable;

import com.the.harbor.base.vo.Response;

public class ResponseData<T> extends Response implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String AJAX_STATUS_SUCCESS = "1";

	public static final String AJAX_STATUS_FAILURE = "0";

	private String statusCode;

	private String statusInfo;

	private String busiCode;

	private T data;

	public ResponseData(String statusCode, String busiCode, String statusInfo) {
		this.statusCode = statusCode;
		this.busiCode = busiCode;
		this.statusInfo = statusInfo;
	}

	public ResponseData(String statusCode, String busiCode, String statusInfo, T data) {
		this.statusCode = statusCode;
		this.busiCode = busiCode;
		this.statusInfo = statusInfo;
		this.data = data;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	

}
