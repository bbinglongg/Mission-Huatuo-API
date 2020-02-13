package com.hase.huatuo.healthcheck.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyFormQuestionItem {
	
	private String item_id;
	private String question_id;
	private String item_no;
	private String item_text_cn;
	private String item_text_en;
	private String need_sub_item;
	private String sub_item_rule;
	
}
