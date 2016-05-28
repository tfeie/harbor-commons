package com.the.harbor.commons.components.datasource;

import com.zaxxer.hikari.HikariDataSource;

public class HarborHikariDataSource extends HikariDataSource {

    public HarborHikariDataSource(String dataSourceName) {
        super(DataSourceUtil.getDBConf(dataSourceName));
    }

}
