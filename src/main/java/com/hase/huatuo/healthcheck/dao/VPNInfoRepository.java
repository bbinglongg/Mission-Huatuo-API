package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.VPNReport;
import com.hase.huatuo.healthcheck.model.VPNStateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VPNInfoRepository extends JpaRepository<VPNStateInfo,String> {
    @Query(value = "select new com.hase.huatuo.healthcheck.model.VPNReport(vpnType, count(vpnType)) from VPNStateInfo where vpnType is not null and vpnType <> '' and lastUpdateDatetime > ?1 group by vpnType")
    List<VPNReport> vpnReport(Date from);
}
