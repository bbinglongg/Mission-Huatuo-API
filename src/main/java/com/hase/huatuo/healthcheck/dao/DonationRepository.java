package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.dao.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation,String> {
}
