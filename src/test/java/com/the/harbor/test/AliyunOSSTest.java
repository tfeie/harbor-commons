package com.the.harbor.test;

import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.aliyuncs.oss.OSSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.components.weixin.WXHelpUtil;
import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.RandomUtil;

public class AliyunOSSTest {

	public static JSONObject uploadFile2OSS(String requestUrl) {
		JSONObject jsonObject = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			// 设置请求方式（GET/POST）
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------" + System.currentTimeMillis();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			InputStream in = conn.getInputStream();
			try {
				putOSS(in);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new SystemException(e.getMessage());
			}

		} catch (ConnectException ce) {
			throw new SDKException("连接异常", ce);
		} catch (Exception e) {
			throw new SDKException("请求异常", e);
		}
		return jsonObject;
	}

	public static void putOSS(InputStream inputStream) throws Throwable {
		OSSClient ossClient = OSSFactory.getOSSClient();

		long time1 = System.currentTimeMillis();
		ossClient.putObject(
				new PutObjectRequest(GlobalSettings.getHarborImagesBucketName(), RandomUtil.generateNumber(6) + ".png",
						inputStream).<PutObjectRequest> withProgressListener(new PutObjectProgressListener()));

		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		// 关闭client
		ossClient.shutdown();
	}

	public static void main(String[] args) throws Throwable {
		String mediaId = "w9enyrJJsgmjJL33F7M176Z4dLIqihSZ8pwDFWAGzFYF_uMf7GW4cuZUyaQ6UyQN";
		String userId ="zhangchao";
		String fileName=WXHelpUtil.uploadUserAuthFileToOSS(mediaId, userId);
		System.out.println(fileName);
	}

	/**
	 * 获取上传进度回调
	 *
	 */
	static class PutObjectProgressListener implements ProgressListener {

		private long bytesWritten = 0;
		private long totalBytes = -1;
		private boolean succeed = false;

		@Override
		public void progressChanged(ProgressEvent progressEvent) {
			long bytes = progressEvent.getBytes();
			ProgressEventType eventType = progressEvent.getEventType();
			switch (eventType) {
			case TRANSFER_STARTED_EVENT:
				System.out.println("Start to upload......");
				break;

			case REQUEST_CONTENT_LENGTH_EVENT:
				this.totalBytes = bytes;
				System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
				break;

			case REQUEST_BYTE_TRANSFER_EVENT:
				this.bytesWritten += bytes;
				if (this.totalBytes != -1) {
					int percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
					System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent
							+ "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
				} else {
					System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "("
							+ this.bytesWritten + "/...)");
				}
				break;

			case TRANSFER_COMPLETED_EVENT:
				this.succeed = true;
				System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
				break;

			case TRANSFER_FAILED_EVENT:
				System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
				break;

			default:
				break;
			}
		}

		public boolean isSucceed() {
			return succeed;
		}
	}

}
