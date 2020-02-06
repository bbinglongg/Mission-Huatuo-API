package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.VpnInfo;
import lombok.Data;

import java.util.List;

/**
 * <p> </p>
 * Created by peng on  2020/2/6 14:40
 */
@Data
public class VpnReportViewResponse extends AdminResponse {

    private List<VpnInfo> items;

    private Integer total;
}
