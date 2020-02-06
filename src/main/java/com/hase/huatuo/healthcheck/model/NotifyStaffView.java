package com.hase.huatuo.healthcheck.model;

import com.hase.huatuo.healthcheck.dao.entity.StaffListEntity;
import lombok.Data;

@Data
public class NotifyStaffView {

    private Notify notify;

    private StaffListEntity staffListEntity;

    public NotifyStaffView(Notify notify,StaffListEntity staffListEntity) {
        this.notify = notify;
        this.staffListEntity = staffListEntity;
    }
}
