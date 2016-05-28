package com.the.harbor.commons.components.datasource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchSettings;
import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.StringUtil;

public final class DataSourceSettings {

    private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
            + System.getProperty("file.separator") + ".harbor-datasources.properties";

    private static final Log log = LogFactory.getLog(ElasticSearchSettings.class);

    private static Properties properties = new Properties();

    static {
        load();
    }

    /**
     * Load settings from the configuration file.
     * <p>
     * The configuration format:<br>
     * xx-db={\"driverClassName\":\"com.mysql.jdbc.Driver\",\"jdbcUrl\":\"jdbc:mysql://x.x.x.x:39306/xdb?useUnicode=true&characterEncoding=UTF-8\",\"username\":\"xname\", \"xpwd\":\"tfeieusr1c908\", \"autoCommit\":\"true\",\"connectionTimeout\":\"30000\", \"idleTimeout\":\"600000\", \"maxLifetime\":\"1800000\", \"maximumPoolSize\":\"10\"}<br>
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
            throw new SDKException("The settings file '" + SETTINGS_FILE_NAME + "' does not exist.");
        } catch (IOException e) {
            log.warn("Failed to load the settings from the file: " + SETTINGS_FILE_NAME);
            throw new SDKException("Failed to load the settings from the file: "
                    + SETTINGS_FILE_NAME);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static JSONObject getDataSourceConfig(String dataSourceName) {
        if (StringUtil.isBlank(dataSourceName)) {
            throw new SDKException(
                    "can not get datasource config because of  dataSourceName is null");
        }
        if (!properties.containsKey(dataSourceName)) {
            throw new SDKException("can not get datasource config by dataSourceName="
                    + dataSourceName + "");
        }
        String dataconfig = properties.getProperty(dataSourceName);
        return JSONObject.parseObject(dataconfig);
    }

}
