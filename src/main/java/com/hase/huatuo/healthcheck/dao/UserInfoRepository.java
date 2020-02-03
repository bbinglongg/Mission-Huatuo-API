package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    @Query(value = "select u from UserInfo u where u.appId=?1 and u.staffId = ?2")
    List<UserInfo> searchRegisterRecord(String appId, String staffId);

    @Query(value = "select u from UserInfo u where u.appId=?1 and u.openId = ?2")
    List<UserInfo> searchOpenIdRegisterRecord(String appId, String openId);
}
