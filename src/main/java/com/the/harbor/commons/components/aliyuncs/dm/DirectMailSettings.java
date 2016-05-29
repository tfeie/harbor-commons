package com.the.harbor.commons.components.aliyuncs.dm;

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

public final class DirectMailSettings {

    private static final String SETTINGS_FILE_NAME = System.getProperty("user.home")
            + System.getProperty("file.separator") + ".aliyun-dm.properties";

    private static final Log log = LogFactory.getLog(ElasticSearchSettings.class);

    private static Properties properties = new Properties();

    static {
        load();
    }

    /**
     * Load settings from the configuration file.
     * <p>
     * The configuration format:<br>
     * dm.regionid=cn-hangzhou<br>
     * dm.accesskeyid=xxx<br>
     * dm.accesskeysecret=xxx<br>
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

    public static String getAccesskeyId() {
        String accesskeyid = properties.getProperty("dm.accesskeyid");
        if (StringUtil.isBlank(accesskeyid)) {
            throw new SDKException("the accesskeyid is null from the file: " + SETTINGS_FILE_NAME);
        }
        return accesskeyid;
    }

    public static String getAccesskeySecret() {
        String accesskeysecret = properties.getProperty("dm.accesskeysecret");
        if (StringUtil.isBlank(accesskeysecret)) {
            throw new SDKException("the accesskeysecret is null from the file: "
                    + SETTINGS_FILE_NAME);
        }
        return accesskeysecret;
    }

    public static String getDMRegionId() {
        String regionId = properties.getProperty("dm.regionid");
        if (StringUtil.isBlank(regionId)) {
            throw new SDKException("the regionid is null from the file: " + SETTINGS_FILE_NAME);
        }
        return regionId;
    }

}
