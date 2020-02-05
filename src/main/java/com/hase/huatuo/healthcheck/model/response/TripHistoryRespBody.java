package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.model.HealthInfo;
import com.hase.huatuo.healthcheck.model.NewsInfo;

public class TripHistoryRespBody {
	
	private String serail_number;
	private String report_date;
	public String getSerail_number() {
		return serail_number;
	}
	public void setSerail_number(String serail_number) {
		this.serail_number = serail_number;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	
}
