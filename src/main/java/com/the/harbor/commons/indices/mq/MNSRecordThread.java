package com.the.harbor.commons.indices.mq;

public class MNSRecordThread implements Runnable {

	private MNSRecord record;

	public MNSRecordThread(MNSRecord record) {
		this.record = record;
	}

	@Override
	public void run() {
		MNSRecordHandle.addMNSRecord(record);
	}

}
