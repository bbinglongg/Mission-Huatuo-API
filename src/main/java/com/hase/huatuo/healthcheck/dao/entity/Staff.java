package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Staff.StaffKey.class)
@Data
public class Staff implements Serializable {
	@Id
    @Column(name="staff_id")
    private String staffId;
    @Column(name="mobile_num")
    private String mobileNum;
    @Column(name="email_address")
    private String emailAddress;
    @Id
    @Column(name="app_id")
    private String appId;
    @Column(name="department_name")
    private String departmentName;

    @Data
    public static class StaffKey implements Serializable {
        private String staffId;
        private String appId;
    }
}
