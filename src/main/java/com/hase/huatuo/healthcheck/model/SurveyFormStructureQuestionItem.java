package com.hase.huatuo.healthcheck.model;

import lombok.Data;

@Data
public class SurveyFormStructureQuestionItem {
	private String itemNo;
	private String itemTextCn;
	private String itemTextEn;
	private String needSubItem;
	private String subItemRule;
}
