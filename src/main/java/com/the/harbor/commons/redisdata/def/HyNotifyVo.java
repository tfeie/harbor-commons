package com.the.harbor.commons.redisdata.def;

import java.io.Serializable;

public class HyNotifyVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String notifyId;

	private String notifyType;

	private String senderType;

	private String senderId;

	private String accepterType;

	private String accepterId;

	private String title;

	private String content;

	private String status;

	private String link;

	// 发送者类型名称
	private String senderTypeName;

	// 发送者用户信息，系统用户默认显示
	private String abroadCountryName;

	private String atCityName;

	private String industryName;

	private String userStatusName;

	private String userTitle;

	private String wxHeadimg;

	private String enName;

	// 是否有连接
	private boolean haslink;

	// 时间描述
	private String timeInterval;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSenderTypeName() {
		return senderTypeName;
	}

	public void setSenderTypeName(String senderTypeName) {
		this.senderTypeName = senderTypeName;
	}

	public String getAbroadCountryName() {
		return abroadCountryName;
	}

	public void setAbroadCountryName(String abroadCountryName) {
		this.abroadCountryName = abroadCountryName;
	}

	public String getAtCityName() {
		return atCityName;
	}

	public void setAtCityName(String atCityName) {
		this.atCityName = atCityName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getWxHeadimg() {
		return wxHeadimg;
	}

	public void setWxHeadimg(String wxHeadimg) {
		this.wxHeadimg = wxHeadimg;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public boolean isHaslink() {
		return haslink;
	}

	public void setHaslink(boolean haslink) {
		this.haslink = haslink;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

}
