package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.model.SurveyFormAnswer;
import com.hase.huatuo.healthcheck.model.SurveyFormQuestion;
import com.hase.huatuo.healthcheck.model.request.SurveyFormReq;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormResponse;

@Service
public class SurveyFormService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String SURVEY_FORM_SQL = "select count(1) from news_info where enable='Y' and app_id =? and id not in (select i.news_id from news_info_read_record i, user_info u where i.staff_id=u.staff_Id and u.open_id=? and i.app_id=?)";

    public ResponseEntity<CommonResponse> getForm(final SurveyFormReq surveyFormReq){
        
    	SurveyFormResponse surveyFormResponse = new SurveyFormResponse();
    	
        // question 1
    	SurveyFormQuestion surveyFormQuestion1 = new SurveyFormQuestion();
        surveyFormQuestion1.setIndex("1");
        surveyFormQuestion1.setQuestion_zh("问题1");
        surveyFormQuestion1.setQuestion_en("Question1");
        surveyFormQuestion1.setMandartory("1");
        // 1. input box 2. radio button 3. textarea
        surveyFormQuestion1.setType("2");
//        Map<String, Map<String, String>> answer1 = new HashMap<>();
//        Map<String, String> option11 = new HashMap<String, String>();
//        option11.put("选项1", "value11");
//        Map<String, String> option12 = new HashMap<String, String>();
//        option12.put("选项2", "value22");
//        answer1.put("1", option11);
//        answer1.put("2", option12);
        List<SurveyFormAnswer> answersList = new ArrayList<>();
        // answer 1
        SurveyFormAnswer answer11 = new SurveyFormAnswer();
        answer11.setAnswer_en("answer11");
        answer11.setAnswer_zh("选项11");
        answer11.setIndex("1");
        answer11.setValue("11");
        answersList.add(answer11);
        
        // answer 1
        SurveyFormAnswer answer12 = new SurveyFormAnswer();
        answer11.setAnswer_en("answer12");
        answer11.setAnswer_zh("选项12");
        answer11.setIndex("2");
        answer11.setValue("12");
        answersList.add(answer12);
        
        surveyFormQuestion1.setAnswers(answersList);
        
        // question 2
        SurveyFormQuestion surveyFormQuestion2 = new SurveyFormQuestion();
        surveyFormQuestion2.setIndex("2");
        surveyFormQuestion2.setQuestion_zh("问题2");
        surveyFormQuestion2.setQuestion_en("Question2");
        surveyFormQuestion2.setMandartory("1");
        // 1. input box 2. radio button 3. checkbox
        surveyFormQuestion2.setType("1");
        
        // question 3
        SurveyFormQuestion surveyFormQuestion3 = new SurveyFormQuestion();
        surveyFormQuestion3.setIndex("3");
        surveyFormQuestion3.setQuestion_zh("问题3");
        surveyFormQuestion3.setQuestion_en("Question3");
        surveyFormQuestion3.setMandartory("1");
        // 1. input box 2. radio button 3. textarea
        surveyFormQuestion3.setType("2");
//        Map<String, Map<String, String>> answer3 = new HashMap<>();
//        Map<String, String> option31 = new HashMap<String, String>();
//        option31.put("选项1", "value31");
//        Map<String, String> option32 = new HashMap<String, String>();
//        option32.put("选项2", "value32");
//        Map<String, String> option33 = new HashMap<String, String>();
//        option33.put("选项32", "value33");
//        answer3.put("1", option31);
//        answer3.put("2", option32);
//        answer3.put("3", option33);
        
        List<SurveyFormAnswer> answersList3 = new ArrayList<>();
        // answer 1
        SurveyFormAnswer answer31 = new SurveyFormAnswer();
        answer31.setAnswer_en("answer31");
        answer31.setAnswer_zh("选项31");
        answer31.setIndex("1");
        answer31.setValue("31");
        answersList3.add(answer31);
        
        // answer 2
        SurveyFormAnswer answer32 = new SurveyFormAnswer();
        answer32.setAnswer_en("answer32");
        answer32.setAnswer_zh("选项32");
        answer32.setIndex("2");
        answer32.setValue("32");
        answersList3.add(answer32);
        
        // answer 3
        SurveyFormAnswer answer33 = new SurveyFormAnswer();
        answer33.setAnswer_en("answer33");
        answer33.setAnswer_zh("选项33");
        answer33.setIndex("3");
        answer33.setValue("33");
        answersList3.add(answer33);
        
        surveyFormQuestion3.setAnswers(answersList3);
        
        // question 4
        SurveyFormQuestion surveyFormQuestion4 = new SurveyFormQuestion();
        surveyFormQuestion4.setIndex("4");
        surveyFormQuestion4.setQuestion_zh("问题4");
        surveyFormQuestion4.setQuestion_en("Question4");
        surveyFormQuestion4.setMandartory("1");
        // 1. input box 2. radio button 3. textarea
        surveyFormQuestion4.setType("3");
        
        List<SurveyFormQuestion> questionList = new ArrayList<>();
        questionList.add(surveyFormQuestion1);
        questionList.add(surveyFormQuestion2);
        questionList.add(surveyFormQuestion3);
        questionList.add(surveyFormQuestion4);
        
        surveyFormResponse.setQuestionList(questionList);
        
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode("200");
        commonResponse.setMsg("success");
        commonResponse.setReturnObject(surveyFormResponse);
        
    	return ResponseEntity.ok(commonResponse);
    }
}
