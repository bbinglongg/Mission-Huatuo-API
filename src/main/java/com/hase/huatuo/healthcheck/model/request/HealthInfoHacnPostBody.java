package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

@Data
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
	private String healthDescription;
	private String goWorkplace;
	private String isolationStartDate;
	private String isolationEndDate;
	
}
