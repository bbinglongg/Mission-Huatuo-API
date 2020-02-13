package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.dao.entity.SurveyFormQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyFormQuestionRepository extends JpaRepository<SurveyFormQuestion, Long> {
//
//    @Query(value = "SELECT SurveyFormQuestion FROM SurveyFormQuestion sfq WHERE sfq.formId = ?1")
//    List<SurveyFormQuestion> retrievalByFormId(Long formId);
}
