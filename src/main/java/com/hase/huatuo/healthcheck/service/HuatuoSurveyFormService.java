package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.model.SurveyFormInfo;
import com.hase.huatuo.healthcheck.model.request.SurveyFormDetailRequestBody;
import com.hase.huatuo.healthcheck.model.request.SurveyFormListRequestBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormDetailResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class HuatuoSurveyFormService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SurveyFormService surveyFormService;

    public ResponseEntity<CommonResponse> getSurveyFormList(SurveyFormListRequestBody surveyFormListRequestBody) {
        CommonResponse commonResponse = new CommonResponse();
        String sql = "SELECT" +
                " sf.form_id AS formId," +
                " sf.form_name_cn AS formNameCn," +
                " sf.form_name_en AS formNameEn," +
                " sf.last_update_datetime AS lastUpdateDateTime," +
                " IF (sfa.form_id IS NULL, '0', '1') AS STATUS" +
                " FROM" +
                " survey_form sf" +
                " LEFT JOIN survey_form_answer sfa ON sfa.form_id = sf.form_id" +
                " AND sfa.staff_id = '"+surveyFormListRequestBody.getStaffId()+"'" +
                " WHERE" +
                " sf.app_id = '"+surveyFormListRequestBody.getAppId()+"'"+
                " ORDER BY sf.last_update_datetime DESC";

        Query query = entityManager.createNativeQuery(sql);
        List rows = query.getResultList();
        List<SurveyFormInfo> unCompleteList = new ArrayList<>();
        List<SurveyFormInfo> completeList = new ArrayList<>();
        SurveyFormListResponse surveyFormListResponse = new SurveyFormListResponse();
        if(rows != null) {
            for(Object obj: rows) {
                SurveyFormInfo item = new SurveyFormInfo();
                Object[] cells = (Object[]) obj;

                item.setFormId(""+cells[0]);
                item.setFormNameCn(""+cells[1]);
                item.setFormNameEn(""+cells[2]);
                item.setLastUpdateDateTime(""+cells[3]);
                item.setStatus(""+cells[4]);
                if("0".equals(""+cells[4])){
                    unCompleteList.add(item);
                }else{
                    completeList.add(item);
                }
            }
        }
        surveyFormListResponse.setComplete(completeList);
        surveyFormListResponse.setUnComplete(unCompleteList);
        commonResponse.setCode("200");
        commonResponse.setMsg("success");
        commonResponse.setReturnObject(surveyFormListResponse);
        return ResponseEntity.ok(commonResponse);
    }

    public ResponseEntity<CommonResponse> getSurveyFormDetail(SurveyFormDetailRequestBody surveyFormDetailRequestBody){
        CommonResponse commonResponse = new CommonResponse();
        String sql = "SELECT * from survey_form_answer sfa where sfa.staff_id = '"+ surveyFormDetailRequestBody.getStaffId()+"'"+
        " AND sfa.form_id='"+ surveyFormDetailRequestBody.getFormId()+"'";

        Query query = entityManager.createNativeQuery(sql);
        List rows = query.getResultList();
        Map<String,String> answersMap = new LinkedHashMap<>();
        if(rows != null) {
            for(Object obj: rows) {
                Object[] cells = (Object[]) obj;
                for(int i=0;i<100;i++){
                    answersMap.put("answer"+(i+1), ObjectUtils.isEmpty(cells[i+3])?"":cells[i+3].toString());
                }
            }
        }

        SurveyFormDetailResponse surveyFormDetailResponse = new SurveyFormDetailResponse();
        surveyFormDetailResponse.setAnswers(answersMap);
        //questions
        surveyFormDetailResponse.setQuestions(surveyFormService.getFormStructure(Long.valueOf(surveyFormDetailRequestBody.getFormId()), surveyFormDetailRequestBody.getAppId()));
        commonResponse.setCode("200");
        commonResponse.setMsg("success");
        commonResponse.setReturnObject(surveyFormDetailResponse);
        return ResponseEntity.ok(commonResponse);
    }
    
}
