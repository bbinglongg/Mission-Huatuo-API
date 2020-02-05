package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class TripDetailReqBody {
	@NotBlank
	private String serailNumber;

	public String getSerailNumber() {
		return serailNumber;
	}

	public void setSerailNumber(String serailNumber) {
		this.serailNumber = serailNumber;
	}
	
}
