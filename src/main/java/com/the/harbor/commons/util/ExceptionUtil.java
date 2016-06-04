package com.the.harbor.commons.util;

import java.io.StringWriter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.alibaba.dubbo.rpc.RpcResult;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.web.model.ResponseData;

public final class ExceptionUtil {

	private ExceptionUtil() {

	}

	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		return stringWriter.getBuffer().toString();
	}

	public static final <T> ResponseData<T> convert(Exception ex, Class<T> clazz) {
		ResponseData<T> responseData = null;
		if (ex instanceof BusinessException) {
			responseData = new ResponseData<T>(ResponseData.AJAX_STATUS_FAILURE, ex.getMessage());
		} else {
			String error = null;
			if (ex.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException ve = (ConstraintViolationException) ex.getCause();
				Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
				if (violations != null && violations.size() > 0) {
					for (ConstraintViolation<?> cv : violations) {
						error = cv.getMessage();
						break;
					}
				}
			}
			responseData = new ResponseData<T>(ResponseData.AJAX_STATUS_FAILURE,
					StringUtil.isBlank(error) ? "系统繁忙，请重试" : error);
		}
		return responseData;
	}

	public static final RpcResult convert(Exception ex) {
		RpcResult r = new RpcResult();
		if (ex.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException ve = (ConstraintViolationException) ex.getCause();
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
			if (violations != null && violations.size() > 0) {
				String error = null;
				for (ConstraintViolation<?> cv : violations) {
					error = cv.getMessage();
					break;
				}
				Response response = new Response();
				response.setResponseHeader(new ResponseHeader(ExceptCodeConstants.VALID_ERROR, error));
				r.setValue(response);
			}
		} else {
			r.setException(ex);
		}
		return r;
	}

}
