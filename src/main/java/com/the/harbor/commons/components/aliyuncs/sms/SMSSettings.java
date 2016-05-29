package com.the.harbor.commons.components.aliyuncs.sms;

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

public final class SMSSettings {

    private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
            + System.getProperty("file.separator") + ".aliqin-sms.properties";

    private static final Log log = LogFactory.getLog(ElasticSearchSettings.class);

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

    public static String getAppKey() {
        String appkey = properties.getProperty("sms.appkey");
        if (StringUtil.isBlank(appkey)) {
            throw new SDKException("the appkey is null from the file: " + SETTINGS_FILE_NAME);
        }
        return appkey;
    }

    public static String getAppSecret() {
        String appsecret = properties.getProperty("sms.appsecret");
        if (StringUtil.isBlank(appsecret)) {
            throw new SDKException("the accesskeysecret is null from the file: "
                    + SETTINGS_FILE_NAME);
        }
        return appsecret;
    }

    public static String getURL() {
        String url = properties.getProperty("sms.url");
        if (StringUtil.isBlank(url)) {
            throw new SDKException("the regionid is null from the file: " + SETTINGS_FILE_NAME);
        }
        return url;
    }

}
