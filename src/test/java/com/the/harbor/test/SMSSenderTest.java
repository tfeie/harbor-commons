package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.aliyuncs.sms.SMSSender;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;

public class SMSSenderTest {

	public static void main(String[] args) {
		SMSSendRequest req = new SMSSendRequest();
		List<String> recNumbers = new ArrayList<String>();
		recNumbers.add("18610316986");
		recNumbers.add("18601179558");
		JSONObject smsParams = new JSONObject();
		smsParams.put("code", "1876");
		smsParams.put("product", "XX平台");
		req.setRecNumbers(recNumbers);
		req.setSmsFreeSignName("注册验证");
		req.setSmsParams(smsParams);
		req.setSmsTemplateCode("SMS_10150871");
		SMSSender.send(req);
	}

}
