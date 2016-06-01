package com.the.harbor.commons.components.globalconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.the.harbor.commons.components.elasticsearch.ElasticSearchSettings;
import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.StringUtil;

public final class GlobalSettings {

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".harbor-global.properties";

	private static final Log log = LogFactory.getLog(ElasticSearchSettings.class);

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
			log.warn("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.");
			throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.");
		} catch (IOException e) {
			log.warn("Failed to load the settings from the file: " + SETTINGS_FILE_NAME);
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
	 * 获取短信验证码的超时时间，单位是秒
	 * 
	 * @return
	 */
	public static final int getSMSRandomCodeExpireSeconds() {
		String seconds = properties.getProperty("sms.randomcode.expire.seconds", "60");
		return Integer.parseInt(seconds);
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
	 * 微信ACCESS_TOKEN获取API
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

}
