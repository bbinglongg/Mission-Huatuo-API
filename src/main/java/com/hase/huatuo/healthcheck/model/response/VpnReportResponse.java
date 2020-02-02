package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.VpnReport;

import java.util.List;

public class VpnReportResponse {

    public VpnReportResponse() {}

    public VpnReportResponse(List<VpnReport> vpnReports) {
        this.vpnReports = vpnReports;
    }

    private List<VpnReport> vpnReports;

    public List<VpnReport> getVpnReports() {
        return vpnReports;
    }

    public void setVpnReports(List<VpnReport> vpnReports) {
        this.vpnReports = vpnReports;
    }
}
