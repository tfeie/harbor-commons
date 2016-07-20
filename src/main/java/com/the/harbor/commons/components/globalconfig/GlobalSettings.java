package com.the.harbor.commons.components.globalconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.StringUtil;

public final class GlobalSettings {

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".harbor-global.properties";

	private static final Logger LOG = Logger.getLogger(GlobalSettings.class);

	private static Properties properties = new Properties();

	static {
		load();
	}

	/**
	 * Load settings from the configuration file.
	 * 
	 * @return
	 */
	public static void load() {
		InputStream is = null;
		try {
			is = new FileInputStream(SETTINGS_FILE_NAME);
			properties.load(is);
		} catch (FileNotFoundException e) {
			LOG.error("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.", e);
			throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.", e);
		} catch (IOException e) {
			LOG.error("Failed to load the settings from the file: " + SETTINGS_FILE_NAME);
			throw new SDKException("Failed to load the settings from the file: " + SETTINGS_FILE_NAME);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 获取域名
	 * 
	 * @return
	 */
	public static final String getHarborDomain() {
		String domain = properties.getProperty("harbor.domain");
		if (StringUtil.isBlank(domain)) {
			throw new SDKException("can not get harbor.domain from the file: " + SETTINGS_FILE_NAME);
		}
		return domain;
	}

	/**
	 * 获取图片文件CDN域名
	 * 
	 * @return
	 */
	public static final String getHarborImagesDomain() {
		String domain = properties.getProperty("images.harbor.domain");
		if (StringUtil.isBlank(domain)) {
			throw new SDKException("can not get images.harbor.domain from the file: " + SETTINGS_FILE_NAME);
		}
		return domain;
	}

	/**
	 * 获取短信验证码的超时时间，单位是秒
	 * 
	 * @return
	 */
	public static final int getSMSRandomCodeExpireSeconds() {
		String seconds = properties.getProperty("sms.randomcode.expire.seconds", "60");
		return Integer.parseInt(seconds);
	}

	/**
	 * 获取阿里亲上的用户随机验证码模板
	 * 
	 * @return
	 */
	public static final String getSMSUserRandomCodeTemplate() {
		String template = properties.getProperty("sms.aliqin.user.randomcode.template");
		if (StringUtil.isBlank(template)) {
			throw new SDKException(
					"can not get sms.aliqin.user.randomcode.template from the file: " + SETTINGS_FILE_NAME);
		}
		return template;
	}

	/**
	 * 获取阿里亲上的签名
	 * 
	 * @return
	 */
	public static final String getSMSFreeSignName() {
		String freeSignName = properties.getProperty("sms.aliqin.freesignname");
		if (StringUtil.isBlank(freeSignName)) {
			throw new SDKException("can not get sms.aliqin.freesignname from the file: " + SETTINGS_FILE_NAME);
		}
		return freeSignName;
	}

	/**
	 * 获取微信应用ID
	 * 
	 * @return
	 */
	public static final String getWeiXinAppId() {
		String appId = properties.getProperty("harbor.weixin.appid");
		if (StringUtil.isBlank(appId)) {
			throw new SDKException("can not get harbor.weixin.appid from the file: " + SETTINGS_FILE_NAME);
		}
		return appId;
	}

	/**
	 * 获取微信应用密钥
	 * 
	 * @return
	 */
	public static final String getWeiXinAppSecret() {
		String appsecret = properties.getProperty("harbor.weixin.appsecret");
		if (StringUtil.isBlank(appsecret)) {
			throw new SDKException("can not get harbor.weixin.appsecret from the file: " + SETTINGS_FILE_NAME);
		}
		return appsecret;
	}

	/**
	 * 获取微信商户号
	 * 
	 * @return
	 */
	public static final String getWeiXinMerchantId() {
		String merchantid = properties.getProperty("harbor.weixin.merchantid");
		if (StringUtil.isBlank(merchantid)) {
			throw new SDKException("can not get harbor.weixin.merchantid from the file: " + SETTINGS_FILE_NAME);
		}
		return merchantid;
	}

	/**
	 * 获取微信商户名称
	 * 
	 * @return
	 */
	public static final String getWeiXinMerchantName() {
		String merchantname = properties.getProperty("harbor.weixin.merchantname");
		if (StringUtil.isBlank(merchantname)) {
			throw new SDKException("can not get harbor.weixin.merchantname from the file: " + SETTINGS_FILE_NAME);
		}
		return merchantname;
	}

	/**
	 * 获取微信支付密钥
	 * 
	 * @return
	 */
	public static final String getWeiXinPaySecret() {
		String paysecret = properties.getProperty("harbor.weixin.paysecret");
		if (StringUtil.isBlank(paysecret)) {
			throw new SDKException("can not get harbor.weixin.paysecret from the file: " + SETTINGS_FILE_NAME);
		}
		return paysecret;
	}

	/**
	 * 微信授权API
	 * 
	 * @return
	 */
	public static final String getWeiXinConnectAuthorizeAPI() {
		String api = properties.getProperty("harbor.weixin.connect.oauth2.authorize.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException(
					"can not get harbor.weixin.connect.oauth2.authorize.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 微信网页认证ACCESS_TOKEN获取API
	 * 
	 * @return
	 */
	public static final String getWeiXinSNSAuthAccessTokenAPI() {
		String api = properties.getProperty("harbor.weixin.sns.oauth2.access_token.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException(
					"can not get harbor.weixin.sns.oauth2.access_token.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 微信用户信息获取API
	 * 
	 * @return
	 */
	public static final String getWeiXinSNSUserInfoAPI() {
		String api = properties.getProperty("harbor.weixin.sns.userinfo.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException("can not get harbor.weixin.sns.userinfo.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 微信商户统一下单API
	 * 
	 * @return
	 */
	public static final String getWeiXinMCHPayUnifiedorderAPI() {
		String api = properties.getProperty("harbor.weixin.mch.payunifiedorder.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException(
					"can not get harbor.weixin.mch.pay.unifiedorder.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 点赞消息处理队列
	 * 
	 * @return
	 */
	public static final String getDianzanQueueName() {
		String queue = properties.getProperty("harbor.mns.dianzan.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.dianzan.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 评论
	 * 
	 * @return
	 */
	public static final String getCommentQueueName() {
		String queue = properties.getProperty("harbor.mns.comment.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.comment.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 收藏
	 * 
	 * @return
	 */
	public static final String getFavorQueueName() {
		String queue = properties.getProperty("harbor.mns.favor.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.favor.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 浏览
	 * 
	 * @return
	 */
	public static final String getViewQueueName() {
		String queue = properties.getProperty("harbor.mns.view.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.view.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * GROUP活动报名确认
	 * 
	 * @return
	 */
	public static final String getGroupJoinConfirmQueueName() {
		String queue = properties.getProperty("harbor.mns.groupjoinconfirm.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException(
					"can not get harbor.mns.groupjoinconfirm.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 站内系统通知消息处理队列
	 * 
	 * @return
	 */
	public static final String getNotifyQueueName() {
		String queue = properties.getProperty("harbor.mns.notify.record.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.notify.record.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 用户资产交易队列
	 * 
	 * @return
	 */
	public static final String getUserAssetsTradeQueueName() {
		String queue = properties.getProperty("harbor.mns.userassets.trade.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException(
					"can not get harbor.mns.userassets.trade.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 获取短信发送数据记录处理队列
	 * 
	 * @return
	 */
	public static final String getSMSRecordQueueName() {
		String queue = properties.getProperty("harbor.mns.sms.record.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.sms.record.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * BE索引异步构建队列
	 * 
	 * @return
	 */
	public static final String getBeIndexBuildQueueName() {
		String queue = properties.getProperty("harbor.mns.be.indexbuild.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.be.indexbuild.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * be索引实时统计数据更新
	 * 
	 * @return
	 */
	public static final String getBeIndexRealtimeCountQueueName() {
		String queue = properties.getProperty("harbor.mns.be.realtimecount.indexupdate.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException(
					"can not get harbor.mns.be.realtimecount.indexupdate.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * go索引实时统计数据更新
	 * 
	 * @return
	 */
	public static final String getGoIndexRealtimeCountQueueName() {
		String queue = properties.getProperty("harbor.mns.go.realtimecount.indexupdate.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException(
					"can not get harbor.mns.go.realtimecount.indexupdate.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * GO索引异步构建队列
	 * 
	 * @return
	 */
	public static final String getGoIndexBuildQueueName() {
		String queue = properties.getProperty("harbor.mns.go.indexbuild.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException("can not get harbor.mns.go.indexbuild.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 用户互动行为消息队列
	 * 
	 * @return
	 */
	public static final String getUserInteractionQueueName() {
		String queue = properties.getProperty("harbor.mns.user.interaction.queue");
		if (StringUtil.isBlank(queue)) {
			throw new SDKException(
					"can not get harbor.mns.user.interaction.queue from the file: " + SETTINGS_FILE_NAME);
		}
		return queue;
	}

	/**
	 * 获取微信普通TOKEN的API
	 * 
	 * @return
	 */
	public static final String getWeiXinTokenAPI() {
		String api = properties.getProperty("harbor.weixin.token.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException("can not get harbor.weixin.token.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 获取微信TICKET的API
	 * 
	 * @return
	 */
	public static final String getWeiXinJSAPITicketAPI() {
		String api = properties.getProperty("harbor.weixin.ticket.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException("can not get harbor.weixin.ticket.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 获取微信媒体文件下载的API
	 * 
	 * @return
	 */
	public static final String getWeiXinMediaGetAPI() {
		String api = properties.getProperty("harbor.weixin.media.get.api");
		if (StringUtil.isBlank(api)) {
			throw new SDKException("can not get harbor.weixin.media.get.api from the file: " + SETTINGS_FILE_NAME);
		}
		return api;
	}

	/**
	 * 获取图片存储OSS的bucket名称
	 * 
	 * @return
	 */
	public static final String getHarborImagesBucketName() {
		String name = properties.getProperty("harbor.oss.images.bucketname");
		if (StringUtil.isBlank(name)) {
			throw new SDKException("can not get harbor.oss.images.bucketname from the file: " + SETTINGS_FILE_NAME);
		}
		return name;
	}

	/**
	 * 获取微信支付结果回调通知地址
	 * 
	 * @return
	 */
	public static final String getHarborWXPayNotifyURL() {
		String url = properties.getProperty("harbor.weixin.pay.notify.url");
		if (StringUtil.isBlank(url)) {
			throw new SDKException("can not get harbor.weixin.pay.notify.url from the file: " + SETTINGS_FILE_NAME);
		}
		return url;
	}

	/**
	 * 用户默认的系统头像地址
	 * 
	 * @return
	 */
	public static final String getHarborUserDefaultHeadICONURL() {
		String url = properties.getProperty("harbor.user.default.headicon.url");
		if (StringUtil.isBlank(url)) {
			throw new SDKException("can not get harbor.user.default.headicon.url from the file: " + SETTINGS_FILE_NAME);
		}
		return url;
	}

	/**
	 * 用户默认的主页头像地址
	 * 
	 * @return
	 */
	public static final String getHarborUserDefaultHomePageBGURL() {
		String url = properties.getProperty("harbor.user.default.homepagebg.url");
		if (StringUtil.isBlank(url)) {
			throw new SDKException(
					"can not get harbor.user.default.homepagebg.url from the file: " + SETTINGS_FILE_NAME);
		}
		return url;
	}

	/**
	 * 获取邀请注册码签名私钥匙
	 * 
	 * @return
	 */
	public static final String getInviteCodePrivateKey() {
		String privateKey = properties.getProperty("harbor.user.invitecode.privateKey");
		if (StringUtil.isBlank(privateKey)) {
			throw new SDKException(
					"can not get harbor.user.invitecode.privateKey from the file: " + SETTINGS_FILE_NAME);
		}
		return privateKey;
	}
}
