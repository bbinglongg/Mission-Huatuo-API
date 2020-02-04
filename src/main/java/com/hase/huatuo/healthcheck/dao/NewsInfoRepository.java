package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.NewsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsInfoRepository extends JpaRepository<NewsInfo, String> {
}
