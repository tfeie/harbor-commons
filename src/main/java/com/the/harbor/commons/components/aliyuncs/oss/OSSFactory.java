package com.the.harbor.commons.components.aliyuncs.oss;

import com.aliyun.oss.OSSClient;

public final class OSSFactory {

	public static OSSClient getOSSClient() {
		String endpoint = OSSSettings.getOSSAccountEndpoint();
		String accessKeyId = OSSSettings.getOSSAccessKeyId();
		String accessKeySecret = OSSSettings.getOSSAccessKeySecret();
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		return ossClient;
	}

}
