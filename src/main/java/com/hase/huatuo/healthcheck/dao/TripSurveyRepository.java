package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.TripSurveyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TripSurveyRepository extends JpaRepository<TripSurveyInfo, String> {

    @Query(value = "select u from TripSurveyInfo u where u.staffId=?1 and u.reportDate = ?2")
    TripSurveyInfo getTripSurveyInfoByStaffId(String staffId, String reportDate);

    @Transactional
    void deleteTripSurveyInfoBySerailNumber(String serailNumber);

}
