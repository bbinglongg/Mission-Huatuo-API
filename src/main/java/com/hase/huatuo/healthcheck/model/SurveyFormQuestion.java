package com.hase.huatuo.healthcheck.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyFormQuestion {

	private Long question_id;
	private String question_title_cn;
	private String question_title_en;
	private String question_no;
	private String question_type;
	private String rule;
	private List<SurveyFormAnswer> answers;
	
}
