package com.the.harbor.commons.components.aliyuncs.mns;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.mns.common.utils.ServiceSettings;

public final class MNSSettings {

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".aliyun-mns.properties";

	private static final Log log = LogFactory.getLog(ServiceSettings.class);

	private static Properties properties = new Properties();

	static {
		load();
	}

	public static String getMNSAccountEndpoint() {
		return properties.getProperty("mns.accountendpoint");
	}

	public static void setMNSAccountEndpoint(String accountEndpoint) {
		properties.setProperty("mns.accountendpoint", accountEndpoint);
	}

	public static String getMNSAccessKeyId() {
		return properties.getProperty("mns.accesskeyid");
	}

	public static void setMNSAccessKeyId(String accessKeyId) {
		properties.setProperty("mns.accesskeyid", accessKeyId);
	}

	public static String getMNSAccessKeySecret() {
		return properties.getProperty("mns.accesskeysecret");
	}

	public static void setMNSAccessKeySecret(String accessKeySecret) {
		properties.setProperty("mns.accesskeysecret", accessKeySecret);
	}

	public static String getMNSSecurityToken() {
		return properties.getProperty("mns.securitytoken");
	}

	public static void setMNSSecurityToken(String securityToken) {
		properties.setProperty("mns.securitytoken", securityToken);
	}

	/**
	 * Load settings from the configuration file.
	 * <p>
	 * The configuration format: mns.endpoint= mns.accesskeyid=
	 * mns.accesskeysecret= proxy.host= proxy.port=
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
			log.warn("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.");
		} catch (IOException e) {
			log.warn("Failed to load the settings from the file: " + SETTINGS_FILE_NAME);
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
