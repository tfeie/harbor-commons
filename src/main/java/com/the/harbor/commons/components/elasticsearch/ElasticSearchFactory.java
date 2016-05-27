package com.the.harbor.commons.components.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.highlight.HighlightField;

import com.the.harbor.commons.exception.SDKException;

public final class ElasticSearchFactory {

    private static TransportClient client;

    private ElasticSearchFactory() {

    }

    public static TransportClient getClient() {
        if (client == null) {
            synchronized (ElasticSearchFactory.class) {
                if (client == null) {
                    Properties properties = ElasticSearchSettings.getProperties();
                    Settings settings = Settings.settingsBuilder().put(properties).build();
                    client = TransportClient.builder().settings(settings).build();
                    try {
                        client.addTransportAddress(new InetSocketTransportAddress(InetAddress
                                .getByName(ElasticSearchSettings.getElasticSearchClusterIp()),
                                ElasticSearchSettings.getElasticSearchClusterPort()));
                    } catch (UnknownHostException e) {
                        throw new SDKException(e);
                    }
                }
            }
        }
        return client;
    }

    public static boolean addIndex(String index, String type, String id, String source) {
        IndexResponse response = getClient()
                .prepareIndex(index.toLowerCase(), type.toLowerCase(), id).setRefresh(true)
                .setSource(source).execute().actionGet();
        boolean result = response.isCreated();
        return result;
    }

    public static void close() {
        getClient().close();
    }

    public static boolean doesIndexExist(String indexName) {
        IndicesExistsRequest ier = new IndicesExistsRequest();
        ier.indices(new String[] { indexName.toLowerCase() });
        return getClient().admin().indices().exists(ier).actionGet().isExists();
    }

    public static boolean doesMappingExist(String indexName, String indexType) {
        TypesExistsRequest ter = new TypesExistsRequest(new String[] { indexName.toLowerCase() },
                indexType);
        return getClient().admin().indices().typesExists(ter).actionGet().isExists();
    }

    public static String getHightLightFieldValue(Map<String, HighlightField> m, String fieldName) {
        if (m == null || fieldName == null || !m.containsKey(fieldName)) {
            return null;
        }
        HighlightField f = m.get(fieldName);
        Text[] titleTexts = f.fragments();
        String name = "";
        for (Text text : titleTexts) {
            name += text;
        }
        return name;
    }

}
