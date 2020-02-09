package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SurveyFormAnswer;
import com.hase.huatuo.healthcheck.model.SurveyFormQuestion;
import com.hase.huatuo.healthcheck.model.request.SurveyFormReq;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;

@Service
public class SurveyFormService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String SURVEY_FORM_SQL = "select q.* from survey_form_question q, survey_form f where q.form_id=? and q.form_id=f.form_id and f.app_id=? order by q.question_no ASC";
    
//    public static final String ANSWER_OPTION_SQL = "select * from survey_form_question_item where question_id in ()";

    public ResponseEntity<CommonResponse> getForm(final SurveyFormReq surveyFormReq){
        
    	CommonResponse commonResponse = new CommonResponse();
    	
	    	RowMapper<SurveyFormQuestion> surveyFormQuestions = BeanPropertyRowMapper.newInstance(SurveyFormQuestion.class);
	    	List<SurveyFormQuestion> questionList = getFormStructure(surveyFormReq.getFormId(), surveyFormReq.getAppId());
	        commonResponse.setCode("200");
	        commonResponse.setMsg("success");
	        commonResponse.setReturnObject(questionList);
    	
        
    	return ResponseEntity.ok(commonResponse);
    }
    
    public List<SurveyFormQuestion> getFormStructure(long formId, String appId) { 
    	try { 
	    	RowMapper<SurveyFormQuestion> surveyFormQuestions = BeanPropertyRowMapper.newInstance(SurveyFormQuestion.class);
	    	List<SurveyFormQuestion> questionList = jdbcTemplate.query(SURVEY_FORM_SQL, surveyFormQuestions, new Object[] {formId, appId});
	    	if (questionList.size()<1) { 
	    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Buss_ERROR", "Can't Find This Form", formId+","+appId);
	    	}
	    	String[] questionIds = new String[questionList.size()];
	    	for (int i=0; i<questionList.size(); i++) { 
	    		questionIds[i] = String.valueOf(questionList.get(i).getQuestion_id());
	    	}
	    	String answerOptionSQL = "select * from survey_form_question_item where question_id in ("+String.join(",", questionIds)+")  order by item_no ASC";
	    	System.out.println(">> answerOptionSQL:"+answerOptionSQL);
	    	RowMapper<SurveyFormAnswer> surveyFormAnswers = BeanPropertyRowMapper.newInstance(SurveyFormAnswer.class);
	    	List<SurveyFormAnswer> answerList = jdbcTemplate.query(answerOptionSQL, surveyFormAnswers);
	    	System.out.println(">> answerList.size():"+answerList.size());
	    	Map<String, List<SurveyFormAnswer>> resultMap = new HashMap<>();
	    	for (int i=0; i<answerList.size(); i++) { 
	    		SurveyFormAnswer temp = answerList.get(i);
	    		String questionId = temp.getQuestion_id();
	    		List<SurveyFormAnswer> list = resultMap.get(questionId);
	    		if (list==null) { 
	    			list = new ArrayList<SurveyFormAnswer>();
	    		}
	    		list.add(temp);
	    		resultMap.put(questionId, list);
	    	}
	    	
	    	for (int i=0; i<questionList.size(); i++) { 
	    		String questionId = String.valueOf(questionList.get(i).getQuestion_id());
	    		questionList.get(i).setAnswers(resultMap.get(questionId));
	    	}
	    	return questionList;
    	} catch (Exception e) { 
    		e.printStackTrace();
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("DB_ERROR", "DB Error", formId+","+appId);
    	}
    	return null;
    }
}
