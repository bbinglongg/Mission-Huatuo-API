package com.hase.huatuo.healthcheck.error;

public class ErrorMessage {
	private String key;
	private String params;
	
	public ErrorMessage(String key) {
		this.key = key;
	}
	public ErrorMessage(String key, String params) {
		this.key = key;
		this.params = params;
	}
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
	
}
