package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.model.HealthInfo;

public class HealthRequestResponse {
	private List<HealthInfo> personHealthInfos;

	public List<HealthInfo> getPersonHealthInfos() {
		return personHealthInfos;
	}

	public void setPersonHealthInfos(List<HealthInfo> personHealthInfos) {
		this.personHealthInfos = personHealthInfos;
	}
	
}
