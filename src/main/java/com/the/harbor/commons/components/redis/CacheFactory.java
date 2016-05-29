package com.the.harbor.commons.components.redis;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.the.harbor.commons.components.redis.impl.CacheClient;
import com.the.harbor.commons.components.redis.impl.CacheClusterClient;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;

public final class CacheFactory {

    private static Map<String, ICacheClient> cacheClients = new ConcurrentHashMap<String, ICacheClient>();

    private CacheFactory() {
    }

    private static ICacheClient getClient(Properties config) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(Integer.parseInt(config.getProperty("redis.maxtotal",
                "500")));
        genericObjectPoolConfig.setMaxIdle(Integer.parseInt(config.getProperty("redis.maxIdle",
                "10")));
        genericObjectPoolConfig.setMinIdle(Integer.parseInt(config
                .getProperty("redis.minIdle", "5")));
        genericObjectPoolConfig.setTestOnBorrow(Boolean.parseBoolean(config.getProperty(
                "redis.testOnBorrow", "true")));

        String host = config.getProperty("redis.host", "127.0.0.1:16379");

        String[] hostArray = host.split(";");
        ICacheClient cacheClient = null;
        if (hostArray.length > 1) {
            cacheClient = new CacheClusterClient(genericObjectPoolConfig, hostArray);
        } else {
            cacheClient = new CacheClient(genericObjectPoolConfig, host);
        }
        cacheClients.put(host, cacheClient);
        return cacheClient;
    }

    public static ICacheClient getClient() {
        Properties config = RedisSettings.getProperties();
        return getClient(config);
    }
}
