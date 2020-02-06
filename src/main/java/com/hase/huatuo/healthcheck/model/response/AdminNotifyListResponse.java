package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.NotifyStaffView;
import com.hase.huatuo.healthcheck.model.VpnReport;
import lombok.Data;

import java.util.List;

@Data
public class AdminNotifyListResponse  extends AdminResponse{

    private List<NotifyStaffView> items;

    private Integer total;
}
