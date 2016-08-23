package com.the.harbor.commons.components.aliyuncs.opensearch;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.object.KeyTypeEnum;
import com.the.harbor.base.exception.SystemException;

public final class OpenSearchFactory {

	public static CloudsearchClient getClient() {
		String accesskey = OpenSearchSettings.getAccessKeyId();
		String secret = OpenSearchSettings.getAccessKeySecret();
		String host = OpenSearchSettings.getHost();
		Map<String, Object> opts = new HashMap<String, Object>();
		try {
			CloudsearchClient client = new CloudsearchClient(accesskey, secret, host, opts, KeyTypeEnum.ALIYUN);
			return client;
		} catch (UnknownHostException e) {
			throw new SystemException(e);
		}
	}

	public static CloudsearchClient getClient(Map<String, Object> opts) {
		String accesskey = OpenSearchSettings.getAccessKeyId();
		String secret = OpenSearchSettings.getAccessKeySecret();
		String host = OpenSearchSettings.getHost();
		try {
			CloudsearchClient client = new CloudsearchClient(accesskey, secret, host, opts, KeyTypeEnum.ALIYUN);
			return client;
		} catch (UnknownHostException e) {
			throw new SystemException(e);
		}
	}

}
