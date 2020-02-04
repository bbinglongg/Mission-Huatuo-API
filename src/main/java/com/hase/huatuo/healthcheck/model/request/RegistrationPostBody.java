package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "RequestBody for datadict request")
public class RegistrationPostBody {
    @ApiModelProperty(value = "appId123456", notes = "app Id", dataType = "java.lang.String")
    private String appId;
    @ApiModelProperty(value = "openId78sasdwde", notes = "open ID get after authorization", dataType = "java.lang.String")
    private String openId;
    @ApiModelProperty(value = "44001234", notes = "user's staffID", dataType = "java.lang.String")
    private String staffId;
    @ApiModelProperty(value = "13999778899", notes = "user's mobileNumber", dataType = "java.lang.String")
    private String mobileNum;
    @ApiModelProperty(value = "1234", notes = "verify code get from SMS", dataType = "java.lang.String")
    private String verifyCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
