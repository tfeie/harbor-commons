package com.the.harbor.commons.components.aliyuncs.oss;

import org.apache.log4j.Logger;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;

public class PutObjectProgressListener implements ProgressListener {

	private static final Logger LOG = Logger.getLogger(PutObjectProgressListener.class);

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
			LOG.debug("Start to upload......");
			break;

		case REQUEST_CONTENT_LENGTH_EVENT:
			this.totalBytes = bytes;
			System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
			LOG.debug(this.totalBytes + " bytes in total will be uploaded to OSS");
			break;

		case REQUEST_BYTE_TRANSFER_EVENT:
			this.bytesWritten += bytes;
			if (this.totalBytes != -1) {
				int percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
				System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%("
						+ this.bytesWritten + "/" + this.totalBytes + ")");
				LOG.debug(bytes + " bytes have been written at this time, upload progress: " + percent + "%("
						+ this.bytesWritten + "/" + this.totalBytes + ")");
			} else {
				System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "("
						+ this.bytesWritten + "/...)");
				LOG.debug(bytes + " bytes have been written at this time, upload ratio: unknown" + "("
						+ this.bytesWritten + "/...)");
			}
			break;

		case TRANSFER_COMPLETED_EVENT:
			this.succeed = true;
			System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
			LOG.debug("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
			break;

		case TRANSFER_FAILED_EVENT:
			System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
			LOG.debug("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
			break;

		default:
			break;
		}
	}

	public boolean isSucceed() {
		return succeed;
	}
}
