package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.aliyuncs.sms.SMSSender;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;

public class SMSSenderTest {

	public static void main(String[] args) {
		SMSSendRequest req = new SMSSendRequest();
		List<String> recNumbers = new ArrayList<String>();
		recNumbers.add("18601179558");
		JSONObject smsParams = new JSONObject();
		smsParams.put("randomCode", "1234");
		req.setRecNumbers(recNumbers);
		req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
		req.setSmsParams(smsParams);
		req.setSmsTemplateCode(GlobalSettings.getSMSUserRandomCodeTemplate());
		SMSSender.send(req); 
	}

}
