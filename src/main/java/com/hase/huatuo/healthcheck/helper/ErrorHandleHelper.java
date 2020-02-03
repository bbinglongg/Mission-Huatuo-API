package com.hase.huatuo.healthcheck.helper;

import com.hase.huatuo.healthcheck.error.ErrorInfo;
import com.hase.huatuo.healthcheck.error.ErrorMessage;
import com.hase.huatuo.healthcheck.error.exception.BadRequestRestException;
import com.hase.huatuo.healthcheck.error.exception.TechnicalRestException;

public class ErrorHandleHelper {

	private static ErrorHandleHelper instance;
	
	public static ErrorHandleHelper getInstance() {
		if(instance == null) {
			instance = new ErrorHandleHelper();
		}
		return instance;
	}
	
	
	public void throwTechnicalRestException(String code, String key, String param) {
		throw new TechnicalRestException(buildErrorInfo(code, key, param));
	}
	
	public void throwBadRequestRestException(String code, String key, String param) {
		throw new BadRequestRestException(buildErrorInfo(code, key, param));
	}
	
	public ErrorInfo buildErrorInfo(String code, String key, String param) {
    	ErrorMessage message = new ErrorMessage(key, param);
    	
    	ErrorInfo errorInfo = new ErrorInfo(code);
    	errorInfo.setMessage(message);
    	
    	return errorInfo; 
    }
}
