package com.the.harbor.test;

import com.the.harbor.commons.redisdata.util.SMSRandomCodeUtil;
import com.the.harbor.commons.util.DateUtil;

public class SMSRandomTest {

	public static void main(String[] args) throws InterruptedException {
		//SMSRandomCodeUtil.setSmsRandomCode("18601179558", "2187");
		while (true) {
			String code = SMSRandomCodeUtil.getSmsRandomCode("18601179558");
			System.out.println("验证码为:" + code + ";时间:" + DateUtil.getSysDate());
			Thread.sleep(5000L);

		}

	}

}
