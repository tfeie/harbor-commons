package com.the.harbor.commons.components.aliyuncs.sms;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
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

	private static final Logger LOG = Logger.getLogger(SMSSender.class);

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
			if (!StringUtil.isBlank(resp.getErrorCode())) {
				throw new SDKException("发送失败:" + resp.getSubMsg());
			}
			boolean success = resp.getResult().getSuccess();
			if (!success) {
				throw new SDKException("发送失败:" + resp.getResult().getMsg());
			}
			status = "10";
			remark = "发送成功";
		} catch (Exception e) {
			// 标记为失败
			status = "11";
			remark = StringUtil.restrictLength("发送失败:" + e.getMessage(), 350);
			LOG.error("短信发送失败", e);
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

		try {
			buildMQandRecord(response);
		} catch (Exception ex) {
			LOG.error("短信发送记录写入消息队列失败", ex);
		}

	}

	private static void buildMQandRecord(SMSSendResponse response) {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getSMSRecordQueueName());
			Message message = new Message();
			message.setMessageBody(JSON.toJSONString(response));
			queue.putMessage(message);
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("SMS  message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

	}

}
