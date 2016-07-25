package com.the.harbor.commons.indices.mq;

public class MNSRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息ID
	 */
	private String mqId;

	/**
	 * 消息类型
	 */
	private String mqType;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 失败原因
	 */
	private String error;

	/**
	 * 消息体内容
	 */
	private Object mqBody;

	/**
	 * 发送时间
	 */
	private String sendDate;

	/**
	 * 消费时间
	 */
	private String consumeDate;

	public String getMqId() {
		return mqId;
	}

	public void setMqId(String mqId) {
		this.mqId = mqId;
	}

	public String getMqType() {
		return mqType;
	}

	public void setMqType(String mqType) {
		this.mqType = mqType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getMqBody() {
		return mqBody;
	}

	public void setMqBody(Object mqBody) {
		this.mqBody = mqBody;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

}
