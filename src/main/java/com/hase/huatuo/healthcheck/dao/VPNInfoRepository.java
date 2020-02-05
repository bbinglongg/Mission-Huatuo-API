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

    @Query(value = "select v.openId, v.staffId, v.location, " +
            "case " +
                "when v.isp='1' then '中国电信 China Telecom' " +
                "when v.isp='2' then '中国移动 China Mobile' " +
                "when v.isp='3' then '中国联通 China Unicom' " +
                "when v.isp='4' then '不知道 Do nott Know' " +
                "else '其它 Others' end as internetISP, " +
            "case " +
                "when v.linkType='1' then '共享 Shared' " +
                "when v.linkType='2' then '独享 Dedicated' " +
                "else '不知道 Do not Know' end as linkType, " +
            "case " +
                "when v.bandWidth='1' then '50Mbps以下 below 50Mbps' " +
                "when v.bandWidth='2' then '50Mbps-100Mbps' " +
                "when v.bandWidth='3' then '100Mbps以上 above 100Mbps' " +
                "else '不知道 Do not Know' end as bandWidth, " +
            "case " +
                "when v.vpnType='1' then '中国大陆VPN CN VPN' " +
                "else '香港VPN HK VPN' end as vpnType, " +
            "case " +
                "when v.hadRebootADSL='1' then '已经重启 YES' " +
                "else '没有重启 NO' end as hadRebootAdsl, " +
            "case " +
                "when v.symptom='1' then '无法登陆VPN Cannot login VPN' " +
                "when v.symptom='2' then 'VPN连接经常会断开 Always disconnect' " +
                "else '其他，请填写第9题 Others, please fill in Question #9' end as symptom, " +
            "case " +
                "when v.outlookSlow='0' then 'No' " +
                "else 'Yes' end as outlookSlow, " +
            "case " +
                "when v.jabberSlow='0' then 'No' " +
                "else 'Yes' end as jabberSlow, " +
            "case " +
                "when v.sametimeSlow='0' then 'No' " +
                "else 'Yes' end as sametimeSlow, " +
            "case " +
                "when v.videoConferenceSlow='0' then 'No' " +
                "else 'Yes' end as vcSlow, " +
            "case " +
                "when v.sharepointSharedFolderSlow='0' then 'No' " +
                "when v.sharepointSharedFolderSlow='1' then 'Yes' " +
                "else '' end as sharepointSharedFolderSlow, " +
            "case " +
                "when v.hasOtherApplicationsSlow='0' then 'No' " +
                "when v.hasOtherApplicationsSlow='1' then 'Yes' " +
                "else '' end as hasOthAppSlow, " +
            "case " +
                "when v.hasSomeApplicationsCannotAccess='0' then 'No' " +
                "when v.hasSomeApplicationsCannotAccess='1' then 'Yes' " +
                "else '' end as hasSomeAppCantAccess, " +

            "v.otherSlowApplications, " +
            "v.cannotAccessApplications, " +
            "v.reporterStaffId, " +
//            "v.havePc, " +
//            "v.haveToken, " +
//            "v.other, " +
//            "v.vpnLocal, " +
//            "v.vpnSate, " +
            "v.lastUpdateDatetime " +


            "from VpnInfo v " +
            "where (?1 is null or v.staffId = ?1) and v.staffId is not null " +
            "and (?2 is null or v.location = ?2) and v.location is not null " +
            "and (?3 is null or v.isp = ?3) and v.isp is not null " +
            "and (?4 is null or ?5 is null or v.lastUpdateDatetime >= ?4 and v.lastUpdateDatetime <= ?5) and v.lastUpdateDatetime is not null "
    )
    List<VpnInfo> vpnReportView(Integer staffId, String location, String internetISP, Date startTime, Date endTime);

}
