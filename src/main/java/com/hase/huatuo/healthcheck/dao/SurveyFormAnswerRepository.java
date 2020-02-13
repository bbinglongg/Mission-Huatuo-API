package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.dao.entity.SurveyFormAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SurveyFormAnswerRepository extends JpaRepository<SurveyFormAnswer, Long> {
    @Query(value = "SELECT DISTINCT sfa FROM SurveyFormAnswer sfa WHERE sfa.formId = ?1 AND sfa.staffId = ?2")
    SurveyFormAnswer getSurveyFormAnswerByFormIdStaffId(Long formId, String staffId);
}
