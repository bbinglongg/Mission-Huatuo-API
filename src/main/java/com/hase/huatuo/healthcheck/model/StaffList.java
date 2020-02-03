package com.hase.huatuo.healthcheck.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="staff_list")
public class StaffList implements Serializable {
	@Id
    @Column(name="staff_id")
    private String staffId;
    @Column(name="mobile_num")
    private String mobileNum;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

}
