package com.hase.huatuo.healthcheck.model.request;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyFormSubmitReq {
	
	@NotBlank
	private Long formId;
	@NotBlank
	private String staffId;
	@NotBlank
	private Map<String, String> answers;

}
