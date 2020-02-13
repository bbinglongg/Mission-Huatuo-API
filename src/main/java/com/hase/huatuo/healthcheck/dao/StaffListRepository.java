package com.hase.huatuo.healthcheck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hase.huatuo.healthcheck.dao.entity.Staff;

@Repository
public interface StaffListRepository extends JpaRepository<Staff,String> {

    @Query(value = "select sl from Staff sl where sl.staffId=?1 and sl.appId = ?2")
    Staff findByStaffIdAppId(String staffId, String appId);
}
