package com.hase.huatuo.healthcheck.model;

public class VPNReport {

    public VPNReport () {}

    public VPNReport (String vpnType, Integer count) {
        this.vpnType = vpnType;
        this.count = count;
    }

    private String vpnType;
    private Integer count;

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
