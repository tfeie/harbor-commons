package com.the.harbor.commons.dubbo.extension;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import net.sf.json.JSONObject;

import com.the.harbor.commons.constants.ExceptCodeConstants;

public class DubboRestResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {
        int status = responseContext.getStatus();
        Object entity = responseContext.getEntity();
        JSONObject data = new JSONObject();
        if (status == 200) {
            if (entity instanceof DubboRestResponse) {
                DubboRestResponse resp = (DubboRestResponse) entity;
                data.put("resultCode", resp.getResultCode());
                data.put("resultMessage", resp.getResultMessage());
            } else {
                data.put("resultCode", ExceptCodeConstants.SUCCESS);
                data.put("resultMessage", "请求成功，业务处理返回请查看data节点");
                data.put("data", responseContext.getEntity());
            }
            responseContext.setEntity(data);
        } else if (status == 204) { 
        }

    }

}