package com.hase.huatuo.healthcheck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hase.huatuo.healthcheck.dao.entity.HealthInfoHacn;
import com.hase.huatuo.healthcheck.model.request.HealthInfoHacnPostBody;

@Repository
public interface HealthInfoHacnRepository extends JpaRepository<HealthInfoHacn,String> {

}
