package com.hase.huatuo.healthcheck.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpStatus;

public class ApiError {
	private ErrorInfo errorInfo;
	private String path;
	private Integer status;
	
	public ApiError(HttpStatus httpStatus, HttpServletRequest request, ErrorInfo errorInfo) {
		this.status = httpStatus.value();
		this.errorInfo = errorInfo;
		this.path = StringEscapeUtils.escapeHtml4(request.getRequestURI());
	}
	
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
