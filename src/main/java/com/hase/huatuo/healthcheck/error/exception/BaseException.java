package com.hase.huatuo.healthcheck.error.exception;

import com.hase.huatuo.healthcheck.error.ErrorInfo;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorInfo errorInfo;

	public BaseException(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	
}
