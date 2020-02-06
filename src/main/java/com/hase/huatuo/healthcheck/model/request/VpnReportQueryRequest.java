package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "the requestBody that Conditional query VPN state report")
public class VpnReportQueryRequest {
    private Integer staffId;
    private String location;
    private String internetISP;
    private String lastUpatetime;
}
