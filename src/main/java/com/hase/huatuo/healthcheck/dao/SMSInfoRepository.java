package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.SMSInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMSInfoRepository extends JpaRepository<SMSInfo,String> {

}
