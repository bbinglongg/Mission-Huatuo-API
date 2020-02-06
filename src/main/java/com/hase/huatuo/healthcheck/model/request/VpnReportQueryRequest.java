package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

/**
 * <p> </p>
 * Created by peng on  2020/2/6 14:44
 */
@Data
public class VpnReportQueryRequest {

    private Integer staffId;

    private String location;

    private String internetISP;

    private String lastUpdatetime;
}
