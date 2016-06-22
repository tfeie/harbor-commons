package com.the.harbor.commons.components.aliyuncs.mns;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;

/**
 * 长轮询检测，内部做了LongPolling的排他机制，只要有一个线程在做LongPolling，那么其他线程只需要Wait就可以了。
 * 
 * @author zhangchao
 *
 */
public class MessageReceiver {

	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	public static final int WAIT_SECONDS = 30;

	// if there are too many queues, a clear method could be involved after
	// deleting the queue
	protected static final Map<String, Object> sLockObjMap = new HashMap<String, Object>();
	protected static Map<String, Boolean> sPollingMap = new ConcurrentHashMap<String, Boolean>();

	protected Object lockObj;
	protected String queueName;
	protected CloudQueue cloudQueue;
	protected int workerId;

	public MessageReceiver(int id, MNSClient mnsClient, String queue) {
		cloudQueue = mnsClient.getQueueRef(queue);
		queueName = queue;
		workerId = id;

		synchronized (sLockObjMap) {
			lockObj = sLockObjMap.get(queueName);
			if (lockObj == null) {
				lockObj = new Object();
				sLockObjMap.put(queueName, lockObj);
			}
		}
	}

	public boolean setPolling() {
		synchronized (lockObj) {
			Boolean ret = sPollingMap.get(queueName);
			if (ret == null || !ret) {
				sPollingMap.put(queueName, true);
				return true;
			}
			return false;
		}
	}

	public void clearPolling() {
		synchronized (lockObj) {
			sPollingMap.put(queueName, false);
			lockObj.notifyAll();
			LOG.debug("Everyone WakeUp and Work!");
		}
	}

	public Message receiveMessage() {
		boolean polling = false;
		while (true) {
			synchronized (lockObj) {
				Boolean p = sPollingMap.get(queueName);
				if (p != null && p) {
					try {
						LOG.debug("Thread" + workerId + " Have a nice sleep!");
						polling = false;
						lockObj.wait();
					} catch (InterruptedException e) {
						LOG.error("MessageReceiver Interrupted! QueueName is " + queueName);
						return null;
					}
				}
			}

			try {
				Message message;
				if (!polling) {
					message = cloudQueue.popMessage();
					if (message == null) {
						polling = true;
						continue;
					}
				} else {
					if (setPolling()) {
						LOG.debug("Thread" + workerId + " Polling!");
					} else {
						continue;
					}
					do {
						LOG.debug("Thread" + workerId + " KEEP Polling!");
						message = cloudQueue.popMessage(WAIT_SECONDS);
					} while (message == null);
					clearPolling();
				}
				return message;
			} catch (Exception e) {
				// it could be network exception
				LOG.error("Exception Happened when popMessage: " + e);
			}
		}
	}
}
