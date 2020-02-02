package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "RequestBody of VPN state report")
public class VpnReportRequest {
    @ApiModelProperty(value = "0", notes = "0 or null means summary from 2019-12-01, 1 means summary of 1 day before", dataType = "java.lang.Integer")
    private Integer day;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
