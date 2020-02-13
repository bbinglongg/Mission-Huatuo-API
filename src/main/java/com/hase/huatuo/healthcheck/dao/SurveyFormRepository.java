package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.dao.entity.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyFormRepository extends JpaRepository<SurveyForm, Long> {

    @Query(value = "SELECT sf FROM SurveyForm sf LEFT JOIN SurveyFormAnswer sfa ON sf.formId = sfa.formId WHERE sf.appId = ?1 AND sfa.staffId = ?2 ORDER BY sf.pushedDatetime DESC")
    List<SurveyForm> retrievalCompleteSurveyFormByAppIdStaffId(String appId, String staffId);

    @Query(value = "SELECT sf" +
                    " FROM SurveyForm sf" +
                    " WHERE sf.appId = ?1" +
                    " AND sf.pushed = '1'" +
                    " ORDER BY sf.pushedDatetime DESC")
    List<SurveyForm> retrievalAllPushedFormByAppId(String appId);
}
