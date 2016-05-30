package com.the.harbor.commons.components.aliyuncs.sms;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest.SMSType;
import com.the.harbor.commons.exception.SDKException;

public final class SMSSender {

	public static void send(SMSSendRequest request) {
		if (request == null) {
			throw new SDKException("短信发送识别，参数为空");
		}
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(request.getExtend());
		req.setSmsType(SMSType.NORMARL.name().toLowerCase());
		req.setSmsFreeSignName(request.getSmsFreeSignName());
		req.setSmsParamString(request.getSmsParams() == null ? null : request.getSmsParams().toJSONString());
		req.setRecNum(StringUtils.join(request.getRecNumbers(), ","));
		req.setSmsTemplateCode(request.getSmsTemplateCode());
		try {
			AlibabaAliqinFcSmsNumSendResponse resp = SMSFactory.getTaobaoClient().execute(req);
			resp.isSuccess();
			System.out.println(JSON.toJSONString(resp));
		} catch (ApiException e) {

		}
	}

}
