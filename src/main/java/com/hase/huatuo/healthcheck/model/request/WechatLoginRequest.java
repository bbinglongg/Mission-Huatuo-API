package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class WechatLoginRequest {
    @NotBlank
    private String appId;
    @NotBlank
    private String code;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
