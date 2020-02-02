package com.hase.huatuo.healthcheck.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "vpn_info")
public class VPNStateInfo {
    @Column(name = "open_id")
    private String openId;
    @Id
    @Column(name = "staff_id", nullable = false)
    private String staffId;
    @Column(name = "location")
    private String location;
    @Column(name = "isp")
    private String isp;
    @Column(name = "link_Type")
    private String linkType;
    @Column(name = "band_width")
    private String bandWidth;
    @Column(name = "vpn_type")
    private String vpnType;
    @Column(name = "had_reboot_adsl")
    private String hadRebootADSL;
    @Column(name = "symptom")
    private String symptom;
    @Column(name = "outlook_slow")
    private String outlookSlow;
    @Column(name = "jabber_slow")
    private String jabberSlow;
    @Column(name = "sametime_slow")
    private String sametimeSlow;
    @Column(name = "video_conference_slow")
    private String videoConferenceSlow;
    @Column(name = "sharepoint_shared_folder_slow")
    private String sharepointSharedFolderSlow;
    @Column(name = "has_other_applications_slow")
    private String hasOtherApplicationsSlow;
    @Column(name = "other_slow_applications")
    private String otherSlowApplications;
    @Column(name = "has_some_applications_cannot_access")
    private String hasSomeApplicationsCannotAccess;
    @Column(name = "cannot_access_applications")
    private String cannotAccessApplications;
    @Column(name = "reporter_staff_id")
    private String reporterStaffId;

    @Column(name = "last_update_datetime")
    private Date lastUpdateDatetime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getBandWidth() {
        return bandWidth;
    }

    public void setBandWidth(String bandWidth) {
        this.bandWidth = bandWidth;
    }

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public String getHadRebootADSL() {
        return hadRebootADSL;
    }

    public void setHadRebootADSL(String hadRebootADSL) {
        this.hadRebootADSL = hadRebootADSL;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getOutlookSlow() {
        return outlookSlow;
    }

    public void setOutlookSlow(String outlookSlow) {
        this.outlookSlow = outlookSlow;
    }

    public String getJabberSlow() {
        return jabberSlow;
    }

    public void setJabberSlow(String jabberSlow) {
        this.jabberSlow = jabberSlow;
    }

    public String getSametimeSlow() {
        return sametimeSlow;
    }

    public void setSametimeSlow(String sametimeSlow) {
        this.sametimeSlow = sametimeSlow;
    }

    public String getVideoConferenceSlow() {
        return videoConferenceSlow;
    }

    public void setVideoConferenceSlow(String videoConferenceSlow) {
        this.videoConferenceSlow = videoConferenceSlow;
    }

    public String getSharepointSharedFolderSlow() {
        return sharepointSharedFolderSlow;
    }

    public void setSharepointSharedFolderSlow(String sharepointSharedFolderSlow) {
        this.sharepointSharedFolderSlow = sharepointSharedFolderSlow;
    }

    public String getHasOtherApplicationsSlow() {
        return hasOtherApplicationsSlow;
    }

    public void setHasOtherApplicationsSlow(String hasOtherApplicationsSlow) {
        this.hasOtherApplicationsSlow = hasOtherApplicationsSlow;
    }

    public String getOtherSlowApplications() {
        return otherSlowApplications;
    }

    public void setOtherSlowApplications(String otherSlowApplications) {
        this.otherSlowApplications = otherSlowApplications;
    }

    public String getHasSomeApplicationsCannotAccess() {
        return hasSomeApplicationsCannotAccess;
    }

    public void setHasSomeApplicationsCannotAccess(String hasSomeApplicationsCannotAccess) {
        this.hasSomeApplicationsCannotAccess = hasSomeApplicationsCannotAccess;
    }

    public String getCannotAccessApplications() {
        return cannotAccessApplications;
    }

    public void setCannotAccessApplications(String cannotAccessApplications) {
        this.cannotAccessApplications = cannotAccessApplications;
    }

    public String getReporterStaffId() {
        return reporterStaffId;
    }

    public void setReporterStaffId(String reporterStaffId) {
        this.reporterStaffId = reporterStaffId;
    }

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }
}
