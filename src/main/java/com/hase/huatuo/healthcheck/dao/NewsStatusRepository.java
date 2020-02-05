package com.hase.huatuo.healthcheck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hase.huatuo.healthcheck.model.ItemStatus;

@Repository
public interface NewsStatusRepository extends JpaRepository<ItemStatus,String> {
}
