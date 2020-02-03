package com.hase.huatuo.healthcheck.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="sms_info")
public class SMSInfo implements Serializable {

    @Id
    @Column(name="mobile_Num",nullable = false)
    private String mobileNum;
    @Column(name="verify_code")
    private String verifyCode;
    @Column(name="create_time")
    private long createtime;

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
