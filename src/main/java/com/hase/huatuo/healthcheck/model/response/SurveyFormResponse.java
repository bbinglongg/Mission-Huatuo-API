package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.model.SurveyFormQuestion;

public class SurveyFormResponse {

	private List<SurveyFormQuestion> questionList;

	public List<SurveyFormQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<SurveyFormQuestion> questionList) {
		this.questionList = questionList;
	}
	
}
