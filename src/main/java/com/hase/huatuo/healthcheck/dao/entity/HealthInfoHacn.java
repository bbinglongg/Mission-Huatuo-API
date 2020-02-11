package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
@Data
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
	@Column(name="go_workplace_datetime")
	private String goWorkplaceDatetime;
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
}
