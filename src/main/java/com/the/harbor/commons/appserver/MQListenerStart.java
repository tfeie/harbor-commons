package com.the.harbor.commons.appserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class MQListenerStart {

	private static final Logger LOG = LoggerFactory.getLogger(MQListenerStart.class);

	private static final String PATH = "classpath:context/mq-listener-context.xml";

	private MQListenerStart() {
	}

	public static void main(String[] args) {
		LOG.error("开始启动MQ监听......");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { PATH });
		context.start();
		LOG.error("监听启动成功.....");
	}
}
