package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.model.HealthReportRecord;

public class HealthReportEnquirytResponse {
	
	private List<HealthReportRecord> healthReportRecordList;

	public List<HealthReportRecord> getHealthReportRecordList() {
		return healthReportRecordList;
	}

	public void setHealthReportRecordList(List<HealthReportRecord> healthReportRecordList) {
		this.healthReportRecordList = healthReportRecordList;
	}
	
}
