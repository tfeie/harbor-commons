package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

import com.the.harbor.base.vo.MNSBody;

/**
 * 产生一个系统消息
 * 
 * @author zhangchao
 *
 */
public class DoNotify extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		PUBLISH, READ, CANCEL;
	}

	private String handleType;

	private String notifyId;

	private String notifyType;

	private String senderType;

	private String senderId;

	private String accepterType;

	private String accepterId;

	private String title;

	private String content;

	private String link;

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getAccepterType() {
		return accepterType;
	}

	public void setAccepterType(String accepterType) {
		this.accepterType = accepterType;
	}

	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
