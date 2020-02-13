package com.hase.huatuo.healthcheck.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity()
@IdClass(UserInfo.UserInfoKey.class)
@Data
public class UserInfo implements Serializable {

    @Column(name="open_Id",nullable = false)
    private String openId;

    @Id
    @Column(name="app_Id",nullable = false)
    private String appId;

    @Id
    @Column(name="staff_id")
    private String staffId;

    @Column(name="mobile_Num")
    private String mobileNum;

    @Column(name="other")
    private String other;

    public static class UserInfoKey implements Serializable {
        private String staffId;
        private String appId;

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
