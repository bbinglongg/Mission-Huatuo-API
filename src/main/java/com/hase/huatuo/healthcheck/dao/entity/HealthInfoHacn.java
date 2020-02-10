package com.hase.huatuo.healthcheck.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class HealthInfoHacn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="serail_number")
	private String serailNumber;
	@Column(name="open_id")
	private String openId;
	@Column(name="staff_id")
	private String staffId;
	@Column(name="staff_name")
	private String staffName;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="is_report_other")
	private String isReportOther;
	@Column(name="report_staff_id")
	private String reportStaffId;
	
	@Column(name="city_short_name")
	private String cityShortName;
	@Column(name="department")
	private String department;
	@Column(name="workplace")
	private String workplace;
	@Column(name="health_status")
	private String healthStatus;
	@Column(name="is_isolation")
	private String isIsolation;
	@Column(name="go_workplace")
	private String goWorkplace;
	@Column(name="health_description")
	private String healthDescription;
	@Column(name="isolation_start_date")
	private String isolationStartDate;
	@Column(name="isolation_end_date")
	private String isolationEndDate;
	@Column(name="report_date")
	private String reportDate;
	@Column(name="is_statistic")
	private String isStatistic;
	
	public String getSerailNumber() {
		return serailNumber;
	}
	public void setSerailNumber(String serailNumber) {
		this.serailNumber = serailNumber;
	}
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
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getIsStatistic() {
		return isStatistic;
	}
	public void setIsStatistic(String isStatistic) {
		this.isStatistic = isStatistic;
	}

	public String getGoWorkplace() {
		return goWorkplace;
	}

	public void setGoWorkplace(String goWorkplace) {
		this.goWorkplace = goWorkplace;
	}

	public String getHealthDescription() {
		return healthDescription;
	}

	public void setHealthDescription(String healthDescription) {
		this.healthDescription = healthDescription;
	}
}
