package com.the.harbor.commons.components.aliyuncs.opensearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class OpenSearchSettings {

	private static final Logger LOG = Logger.getLogger(OpenSearchSettings.class);

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".aliyun-opensearch.properties";

	private static Properties properties = new Properties();

	static {
		load();
	}

	public static String getAppName() {
		return properties.getProperty("opensearch.appname");
	}
	
	public static String getHost() {
		return properties.getProperty("opensearch.host");
	}

	public static String getAccessKeyId() {
		return properties.getProperty("opensearch.accesskeyid");
	}

	public static String getAccessKeySecret() {
		return properties.getProperty("opensearch.accesskeysecret");
	}

	/**
	 * Load settings from the configuration file.
	 * <p>
	 * The configuration format:<br>
	 * oss.endpoint= <br>
	 * oss.accesskeyid=<br>
	 * oss.accesskeysecret= <br>
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
		} catch (IOException e) {
			LOG.error("Failed to load the settings from the file: " + SETTINGS_FILE_NAME, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
