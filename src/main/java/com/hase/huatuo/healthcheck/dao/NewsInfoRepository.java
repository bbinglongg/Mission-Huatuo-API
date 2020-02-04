package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.HealthInfo;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.healthPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsInfoRepository extends JpaRepository<NewsInfo, String> {

    @Query(nativeQuery = true,value = "SELECT ni.* FROM news_info ni WHERE ni.enable='Y' AND ni.priority=1 ORDER BY ni.date DESC LIMIT 0,5")
    List<NewsInfo> getImportantNewsList();
}
