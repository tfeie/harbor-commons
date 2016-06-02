package com.the.harbor.commons.components.aliyuncs.sms;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.taobao.api.ApiException;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest.SMSType;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendResponse;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.exception.SDKException;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;

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

		String remark = "";
		String status = "";
		AlibabaAliqinFcSmsNumSendResponse resp = null;
		try {
			resp = SMSFactory.getTaobaoClient().execute(req);
			boolean success = resp.getResult().getSuccess();
			if (success) {
				status = "10";
				remark = "发送成功";
			} else {
				status = "11";
				remark = StringUtil.restrictLength("发送失败:" + resp.getResult().getMsg(), 350);
			}
		} catch (ApiException e) {
			// 标记为失败
			status = "11";
			remark = StringUtil.restrictLength("发送失败:" + e.getErrMsg(), 350);
		}
		// 组织短信发送模板消息
		SMSSendResponse response = new SMSSendResponse();
		response.setRecordId(UUIDUtil.genId32());
		response.setPhoneNumbers(req.getRecNum());
		response.setCreateDate(DateUtil.getSysDate());
		response.setRemark(remark);
		response.setReqBody(JSON.toJSONString(req));
		response.setRespBody(resp == null ? null : JSON.toJSONString(resp));
		response.setTemplateCode(req.getSmsTemplateCode());
		response.setSmsContent(req.getSmsParam());
		response.setStatus(status);
		buildMQandRecord(response);
	}

	private static void buildMQandRecord(SMSSendResponse response) {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getSMSRecordQueueName());
			Message message = new Message();
			message.setMessageBody(JSON.toJSONString(response));
			queue.putMessage(message);
		} catch (ClientException ce) {
			System.out.println("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.");
			ce.printStackTrace();
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				System.out.println("Queue is not exist.Please create before use");
			} else if (se.getErrorCode().equals("TimeExpired")) {
				System.out.println("The request is time expired. Please check your local machine timeclock");
			}
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknown exception happened!");
			e.printStackTrace();
		}
		client.close();

	}

}
