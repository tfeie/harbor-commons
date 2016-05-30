package com.the.harbor.commons.components.aliyuncs.mns;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.utils.ServiceSettings;

public final class MNSFactory {

	public static MNSClient getMNSClient() {
		CloudAccount account = new CloudAccount(ServiceSettings.getMNSAccessKeyId(),
				ServiceSettings.getMNSAccessKeySecret(), ServiceSettings.getMNSAccountEndpoint());
		MNSClient client = account.getMNSClient();
		return client;
	}

}
