package com.hase.huatuo.healthcheck.model;

public class HacnIsolationInfoView {
    public String getIsolationType() {
        return isolationType;
    }

    public void setIsolationType(String isolationType) {
        this.isolationType = isolationType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String isolationType;
    private String total;
}
