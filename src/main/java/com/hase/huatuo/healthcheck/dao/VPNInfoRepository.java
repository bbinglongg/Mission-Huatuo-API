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

    @Query(value = "select v.open_id, v.staff_Id, v.location, " +
            "case " +
                "when v.isp='1' then '中国电信 China Telecom' " +
                "when v.isp='2' then '中国移动 China Mobile' " +
                "when v.isp='3' then '中国联通 China Unicom' " +
                "when v.isp='4' then '不知道 Do nott Know' " +
                "else '其它 Others' end as isp, " +
            "case " +
                "when v.link_Type='1' then '共享 Shared' " +
                "when v.link_Type='2' then '独享 Dedicated' " +
                "else '不知道 Do not Know' end as link_Type, " +
            "case " +
                "when v.band_width='1' then '50Mbps以下 below 50Mbps' " +
                "when v.band_width='2' then '50Mbps-100Mbps' " +
                "when v.band_width='3' then '100Mbps以上 above 100Mbps' " +
                "else '不知道 Do not Know' end as band_width, " +
            "case " +
                "when v.vpn_type='1' then '中国大陆VPN CN VPN' " +
                "else '香港VPN HK VPN' end as vpn_type, " +
            "case " +
                "when v.had_reboot_adsl='1' then '已经重启 YES' " +
                "else '没有重启 NO' end as had_reboot_adsl, " +
            "case " +
                "when v.symptom='1' then '无法登陆VPN Cannot login VPN' " +
                "when v.symptom='2' then 'VPN连接经常会断开 Always disconnect' " +
                "else '其他，请填写第9题 Others, please fill in Question #9' end as symptom, " +
            "case " +
                "when v.outlook_slow='0' then 'No' " +
                "else 'Yes' end as outlook_slow, " +
            "case " +
                "when v.jabber_slow='0' then 'No' " +
                "else 'Yes' end as jabber_slow, " +
            "case " +
                "when v.sametime_slow='0' then 'No' " +
                "else 'Yes' end as sametime_slow, " +
            "case " +
                "when v.video_conference_slow='0' then 'No' " +
                "else 'Yes' end as video_conference_slow, " +
            "case " +
                "when v.sharepoint_shared_folder_slow='0' then 'No' " +
                "when v.sharepoint_shared_folder_slow='1' then 'Yes' " +
                "else '' end as sharepoint_shared_folder_slow, " +
            "case " +
                "when v.has_other_applications_slow='0' then 'No' " +
                "when v.has_other_applications_slow='1' then 'Yes' " +
                "else '' end as has_other_applications_slow, " +
            "case " +
                "when v.has_some_applications_cannot_access='0' then 'No' " +
                "when v.has_some_applications_cannot_access='1' then 'Yes' " +
                "else '' end as has_some_applications_cannot_access, " +

            "v.other_slow_applications, " +
            "v.cannot_access_applications, " +
            "v.reporter_staff_id, " +
//            "v.havePc, " +
//            "v.haveToken, " +
//            "v.other, " +
//            "v.vpnLocal, " +
//            "v.vpnSate, " +
            "v.last_update_datetime " +
            "from vpn_info v " +
            "where (?1 is null or v.staff_Id = ?1) and v.staff_Id is not null " +
            "and (?2 is null or v.location like ?2) and v.location is not null " +
            "and (?3 is null or v.isp = ?3) and v.isp is not null " +
            "and (?4 is null or v.last_update_datetime like ?4) " +
            "and v.last_update_datetime is not null ",
            nativeQuery = true
    )
    List<VpnInfo> vpnReportView(Integer staffId, String location, String internetISP, String lastUpdatetime);

}
