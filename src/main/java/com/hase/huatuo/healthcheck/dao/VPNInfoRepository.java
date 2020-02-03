package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.VpnReport;
import com.hase.huatuo.healthcheck.model.VpnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VPNInfoRepository extends JpaRepository<VpnInfo,String> {
    @Query(value = "select new com.hase.huatuo.healthcheck.model.VpnReport(vpnType, count(vpnType)) from VpnInfo where vpnType is not null and vpnType <> '' and lastUpdateDatetime > ?1 group by vpnType")
    List<VpnReport> vpnReport(Date from);
}
