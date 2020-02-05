package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class TripHistoryReqBody {
	@NotBlank
	private String staffId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
}
