package com.hase.huatuo.healthcheck.error.exception;

import com.hase.huatuo.healthcheck.error.ErrorInfo;

public class TechnicalRestException extends BaseException {
	private static final long serialVersionUID = 1L;
	
	public TechnicalRestException(ErrorInfo errorInfo) {
		super(errorInfo);
	}

}
