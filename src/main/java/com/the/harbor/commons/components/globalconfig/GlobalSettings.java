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
	 * 获取短信验证码的超时时间，单位是秒
	 * 
	 * @return
	 */
	public static final int getSMSRandomCodeExpireSeconds() {
		String seconds = properties.getProperty("sms.randomcode.expire.seconds", "60");
		return Integer.parseInt(seconds);
	}

}
