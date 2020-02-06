package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.StaffOfHacnNeedsCollection;
import com.hase.huatuo.healthcheck.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffNeedsCollectionsRepository extends JpaRepository<StaffOfHacnNeedsCollection, String> {
    @Query(value = "select s from StaffOfHacnNeedsCollection s where s.staffId=?1 and s.createDate= ?2")
    List<StaffOfHacnNeedsCollection> searchRegisterRecord(String staffId, String createDate);
}
