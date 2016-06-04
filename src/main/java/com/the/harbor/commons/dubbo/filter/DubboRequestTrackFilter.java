package com.the.harbor.commons.dubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.fastjson.JSON;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.ExceptionUtil;
import com.the.harbor.commons.util.UUIDUtil;

@Activate(group = { Constants.PROVIDER })
public class DubboRequestTrackFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(DubboRequestTrackFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) {
		String reqSV = invoker.getInterface().getName();
		String reqMethod = invocation.getMethodName();
		Object[] requestParams = invocation.getArguments();
		// 交易序列
		String tradeSeq = UUIDUtil.genId32();
		// 打印请求参数明细
		if (CollectionUtil.isEmpty(requestParams)) {
			if (LOG.isInfoEnabled()) {
				LOG.info("TRADE_SEQ:{},请求接口:{},请求方法:{},请求参数:{}", tradeSeq, reqSV, reqMethod, "");
			}
		} else {
			if (LOG.isInfoEnabled()) {
				LOG.info("TRADE_SEQ:{},请求接口:{},请求方法:{},请求参数:{}", tradeSeq, reqSV, reqMethod,
						JSON.toJSONString(requestParams));
			}
		}
		// 执行结果
		Result result = null;
		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("TRADE_SEQ:{},执行调用服务{}类中的{}方法", tradeSeq, reqSV, reqMethod);
			}
			result = invoker.invoke(invocation);
			if (result.hasException()) {
				Throwable e = result.getException();
				if (LOG.isErrorEnabled()) {
					LOG.error("TRADE_SEQ:{},调用服务{}类中的{}方法发生异常，原因:{}", tradeSeq, reqSV, reqMethod,
							result.getException().getMessage(), result.getException());
				}
				if (e instanceof BusinessException) {
					Response response = new Response();
					response.setResponseHeader(new ResponseHeader(((BusinessException) e).getErrorCode(),
							((BusinessException) e).getErrorMessage()));
					RpcResult r = new RpcResult();
					r.setValue(response);
					return r;
				} else if (e instanceof SystemException) {
					throw (SystemException) e;
				} else {
					throw new SystemException((Exception) e);
				}
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("TRADE_SEQ:{},调用服务{}类中的{}方法的结果:{}", tradeSeq, reqSV, reqMethod,
						JSON.toJSONString(result.getValue()));
			}
			return result;
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error("TRADE_SEQ:{},执行{}类中的{}方法发生异常:{}", tradeSeq, reqSV, reqMethod, ex);
			}
			RpcResult r = ExceptionUtil.convert(ex);
			return r;
		}

	}

}
