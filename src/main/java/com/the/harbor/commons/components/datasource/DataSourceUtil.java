package com.the.harbor.commons.components.datasource;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.exception.SDKException;
import com.zaxxer.hikari.HikariConfig;

public final class DataSourceUtil {

    public static HikariConfig getDBConf(String dataSourceName) {
        JSONObject data = DataSourceSettings.getDataSourceConfig(dataSourceName);
        if (data == null) {
            throw new SDKException("cann't get database config info of dataSourceName["
                    + dataSourceName + "]");
        }
        HikariConfig dbconf = JSONObject.toJavaObject(data, HikariConfig.class);
        return dbconf;
    }

}
