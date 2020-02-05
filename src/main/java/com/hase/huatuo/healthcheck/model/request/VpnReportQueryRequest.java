package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "the requestBody that Conditional query VPN state report")
public class VpnReportQueryRequest {
    private Integer staffId;
    private String location;
    private String internetISP;
    private Date lastUpatetime;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInternetISP() {
        return internetISP;
    }

    public void setInternetISP(String internetISP) {
        this.internetISP = internetISP;
    }

    public Date getLastUpatetime() {
        return lastUpatetime;
    }

    public void setLastUpatetime(Date lastUpatetime) {
        this.lastUpatetime = lastUpatetime;
    }
}
