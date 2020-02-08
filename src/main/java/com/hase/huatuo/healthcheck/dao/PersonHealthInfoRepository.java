package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.HealthInfo;
import com.hase.huatuo.healthcheck.model.healthPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonHealthInfoRepository extends JpaRepository<HealthInfo,healthPK> {
    @Query(value = "select h from HealthInfo h where h.id.staffID = ?1")
    List<HealthInfo> searchSavedRecordByStaffId(String staffID);
}
