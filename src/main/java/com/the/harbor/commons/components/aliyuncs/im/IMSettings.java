package com.the.harbor.commons.components.aliyuncs.im;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.StringUtil;

public final class IMSettings {

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".harbor-im.properties";

	private static final Logger LOG = Logger.getLogger(IMSettings.class);

	private static Properties properties = new Properties();

	static {
		load();
	}

	/**
	 * Load settings from the configuration file.
	 * <p>
	 * The configuration format:<br>
	 * sms.url=xx<br>
	 * sms.appkey=xxx<br>
	 * sms.appsecret=xxx<br>
	 * </p>
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
			throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.");
		} catch (IOException e) {
			LOG.error("Failed to load the settings from the file: " + SETTINGS_FILE_NAME, e);
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

	public static String getAppKey() {
		String appkey = properties.getProperty("im.appkey");
		if (StringUtil.isBlank(appkey)) {
			throw new SDKException("the appkey is null from the file: " + SETTINGS_FILE_NAME);
		}
		return appkey;
	}

	public static String getAppSecret() {
		String appsecret = properties.getProperty("im.appsecret");
		if (StringUtil.isBlank(appsecret)) {
			throw new SDKException("the accesskeysecret is null from the file: " + SETTINGS_FILE_NAME);
		}
		return appsecret;
	}

	public static String getURL() {
		String url = properties.getProperty("im.url");
		if (StringUtil.isBlank(url)) {
			throw new SDKException("the url is null from the file: " + SETTINGS_FILE_NAME);
		}
		return url;
	}

}
