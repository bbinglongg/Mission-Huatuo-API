package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.VPNReport;

import java.util.List;

public class VPNReportResponse {

    public VPNReportResponse () {}

    public VPNReportResponse (List<VPNReport> vpnReports) {
        this.vpnReports = vpnReports;
    }

    private List<VPNReport> vpnReports;

    public List<VPNReport> getVpnReports() {
        return vpnReports;
    }

    public void setVpnReports(List<VPNReport> vpnReports) {
        this.vpnReports = vpnReports;
    }
}
