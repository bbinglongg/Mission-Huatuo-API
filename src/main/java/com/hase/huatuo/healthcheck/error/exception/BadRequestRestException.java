package com.hase.huatuo.healthcheck.error.exception;

import com.hase.huatuo.healthcheck.error.ErrorInfo;

public class BadRequestRestException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	
	public BadRequestRestException(ErrorInfo errorInfo) {
		super(errorInfo);
	}

}
