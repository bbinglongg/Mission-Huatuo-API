package com.hase.huatuo.healthcheck.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyFormQuestion {

	private String question_zh;
	private String question_en;
	private String index;
	private String mandartory;
	private String type;
	private List<SurveyFormAnswer> answers;
	
}
