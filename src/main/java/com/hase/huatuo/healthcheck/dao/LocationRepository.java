package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,String> {
}
