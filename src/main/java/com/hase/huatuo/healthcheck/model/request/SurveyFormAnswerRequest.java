package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotNull;

import com.hase.huatuo.healthcheck.model.SurveyFormData;
import lombok.Data;

@Data
public class SurveyFormAnswerRequest {
	@NotNull
	private SurveyFormData answers;
}
