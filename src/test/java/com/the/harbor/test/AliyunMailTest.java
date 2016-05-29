package com.the.harbor.test;

import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.the.harbor.commons.components.aliyuncs.dm.DirectMailFactory;

public class AliyunMailTest {

    public static void main(String[] args) {
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("admin@notice.tfeie.com");
            request.setFromAlias("admin");
            request.setAddressType(1);
            request.setTagName("注册");
            request.setReplyToAddress(true);
            request.setToAddress("choaryzhang@163.com");
            request.setSubject("欢迎加入海归海湾哦");
            request.setHtmlBody("您的注册验证码为：1234");
            SingleSendMailResponse httpResponse = DirectMailFactory.getIAcsClient().getAcsResponse(
                    request);
            System.out.println(httpResponse.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
