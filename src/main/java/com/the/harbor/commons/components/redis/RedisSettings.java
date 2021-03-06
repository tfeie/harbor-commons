package com.the.harbor.commons.components.redis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.StringUtil;

public final class RedisSettings {

	private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
			+ System.getProperty("file.separator") + ".harbor-redis.properties";

	private static final Logger LOG = Logger.getLogger(RedisSettings.class);

	private static Properties properties = new Properties();

	static {
		load();
	}

	/**
	 * Load settings from the configuration file.
	 * <p>
	 * The configuration format:<br>
	 * redis.maxtotal=500<br>
	 * redis.maxIdle=10<br>
	 * redis.minIdle=5<br>
	 * redis.testOnBorrow=true<br>
	 * redis.host=127.0.0.1:16379<br>
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

	public static Properties getProperties() {
		return properties;
	}

	public static String getElasticSearchClusterIp() {
		String ip = properties.getProperty("cluster.ip");
		if (StringUtil.isBlank(ip)) {
			throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist [cluster.ip]");
		}
		return ip;
	}

	public static int getElasticSearchClusterPort() {
		String p = properties.getProperty("cluster.port");
		if (StringUtil.isBlank(p)) {
			throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist [cluster.port]");
		}
		return Integer.parseInt(p);
	}

}
