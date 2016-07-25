package com.the.harbor.commons.indices.mq;

import java.io.Serializable;

public class MNSRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public enum Status implements Serializable {
		SEND_SUCCESS, SEND_FAIL, CONSUME_SUCCESS, CONSUME_FAIL;
	}

	/**
	 * 消息ID
	 */
	private String mqId;

	/**
	 * 消息类型
	 */
	private String mqType;

	/**
	 * 发送状态
	 */
	private String sendStatus;

	/**
	 * 消费状态
	 */
	private String consumeStatus;

	/**
	 * 发送失败原因
	 */
	private String sendError;

	/**
	 * 消费失败原因
	 */
	private String consumeError;

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

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getConsumeStatus() {
		return consumeStatus;
	}

	public void setConsumeStatus(String consumeStatus) {
		this.consumeStatus = consumeStatus;
	}

	public String getSendError() {
		return sendError;
	}

	public void setSendError(String sendError) {
		this.sendError = sendError;
	}

	public String getConsumeError() {
		return consumeError;
	}

	public void setConsumeError(String consumeError) {
		this.consumeError = consumeError;
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
