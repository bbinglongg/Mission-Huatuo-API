package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SurveyFormListRequest {
	@NotBlank
	private String appId;

	@NotBlank
	private String staffId;
}
