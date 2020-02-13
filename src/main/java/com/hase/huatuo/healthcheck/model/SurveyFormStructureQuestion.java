package com.hase.huatuo.healthcheck.model;

import lombok.Data;

import java.util.List;

@Data
public class SurveyFormStructureQuestion {
	private String questionNo;
	private String questionType;
	private String questionTitleCn;
	private String questionTitleEn;
	private String rule;
	private List<SurveyFormStructureQuestionItem> questionItems;
}
