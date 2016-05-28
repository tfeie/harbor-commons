package com.the.harbor.test;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.components.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariConfig;

public class DataSourceTest {

    public static void main(String[] args) {

        HikariConfig config = DataSourceUtil.getDBConf("harbor-db");
        System.out.println(JSON.toJSONString(config));

    }

}
