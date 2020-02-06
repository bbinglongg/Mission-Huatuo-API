package com.hase.huatuo.healthcheck.model.request;

public class HealthInfoHacnPostBody {
	
	private String openId;
	private String staffId;
	private String staffName;
	private String mobileNumber;
	private String isReportOther;
	private String reportStaffId;
	private String cityShortName;
	private String department;
	private String workplace;
	private String healthStatus;
	private String isIsolation;
	private String isolationType;
	private String isolationStartDate;
	private String isolationEndDate;

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getIsReportOther() {
		return isReportOther;
	}
	public void setIsReportOther(String isReportOther) {
		this.isReportOther = isReportOther;
	}
	public String getReportStaffId() {
		return reportStaffId;
	}
	public void setReportStaffId(String reportStaffId) {
		this.reportStaffId = reportStaffId;
	}
	public String getCityShortName() {
		return cityShortName;
	}
	public void setCityShortName(String cityShortName) {
		this.cityShortName = cityShortName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getIsIsolation() {
		return isIsolation;
	}
	public void setIsIsolation(String isIsolation) {
		this.isIsolation = isIsolation;
	}
	public String getIsolationType() {
		return isolationType;
	}
	public void setIsolationType(String isolationType) {
		this.isolationType = isolationType;
	}
	public String getIsolationStartDate() {
		return isolationStartDate;
	}
	public void setIsolationStartDate(String isolationStartDate) {
		this.isolationStartDate = isolationStartDate;
	}
	public String getIsolationEndDate() {
		return isolationEndDate;
	}
	public void setIsolationEndDate(String isolationEndDate) {
		this.isolationEndDate = isolationEndDate;
	}
	
}
