package com.hase.huatuo.healthcheck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hase.huatuo.healthcheck.model.StaffList;

@Repository
public interface StaffListRepository extends JpaRepository<StaffList,String> {

}
