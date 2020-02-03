package com.hase.huatuo.healthcheck.error;

public class ErrorInfo {
	private String code;
	private ErrorMessage message;
	
	public ErrorInfo(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ErrorMessage getMessage() {
		return message;
	}
	public void setMessage(ErrorMessage message) {
		this.message = message;
	}
	
}
