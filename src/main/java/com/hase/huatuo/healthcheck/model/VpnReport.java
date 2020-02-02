package com.hase.huatuo.healthcheck.model;

public class VpnReport {

    public VpnReport() {}

    public VpnReport(String vpnType, long count) {
        this.vpnType = vpnType;
        this.count = count;
    }

    private String vpnType;
    private long count;

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
