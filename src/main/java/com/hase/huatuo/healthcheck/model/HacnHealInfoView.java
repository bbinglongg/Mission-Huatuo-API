package com.hase.huatuo.healthcheck.model;

public class HacnHealInfoView {
    public String getCityShortName() {
        return cityShortName;
    }

    public void setCityShortName(String cityShortName) {
        this.cityShortName = cityShortName;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String cityShortName;
    private String healthStatus;

    public String getIsSubBranch() {
        return isSubBranch;
    }

    public void setIsSubBranch(String isSubBranch) {
        this.isSubBranch = isSubBranch;
    }

    private String isSubBranch;
    private String total;
}
