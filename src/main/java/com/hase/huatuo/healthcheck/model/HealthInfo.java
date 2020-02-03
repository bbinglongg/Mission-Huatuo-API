package com.hase.huatuo.healthcheck.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
public class HealthInfo implements Serializable {
	
	@Column(name="open_Id")
	private String openId;
	@Id
	private healthPK id;
	@Column(name="mobile_Num")
	private String mobileNum;
	@Column(name="department")
	private String department;
	@Column(name="city")
	private String city;
	@Column(name="health_Status")
	private String healthStatus;
	@Column(name="reporter")
	private String reporter;
	@Column(name="other")
	private String other;
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public healthPK getId() {
		return id;
	}
	public void setId(healthPK id) {
		this.id = id;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
}
