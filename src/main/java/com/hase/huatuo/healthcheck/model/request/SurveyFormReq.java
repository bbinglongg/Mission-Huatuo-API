package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class SurveyFormReq {
	@NotBlank
	private long formId;
	@NotBlank
	private String appId;
	private String staffId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public long getFormId() {
		return formId;
	}
	public void setFormId(long formId) {
		this.formId = formId;
	}
	
}
