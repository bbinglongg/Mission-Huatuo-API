package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 * @date
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    /**
     * search user info by appId and staffId
     * @param appId
     * @param staffId
     * @return
     */
    @Query(value = "select u from UserInfo u where u.appId=?1 and u.staffId = ?2")
    List<UserInfo> retrieveUserInfoByAppIdStaffId(String appId, String staffId);

    /**
     * search user info by appId and openId
     * @param appId
     * @param openId
     * @return
     */
    @Query(value = "select u from UserInfo u where u.appId=?1 and u.openId = ?2")
    List<UserInfo> retrieveUserInfoByAppIdOpenId(String appId, String openId);
}
