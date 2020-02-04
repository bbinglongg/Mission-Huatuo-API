package com.hase.huatuo.healthcheck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hase.huatuo.healthcheck.dao.entity.NewsInfoEntity;

@Repository
public interface NewsInfoListRepository extends JpaRepository<NewsInfoEntity,String> {
	
}
