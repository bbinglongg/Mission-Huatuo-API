package com.hase.huatuo.healthcheck.model.response;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.hase.huatuo.healthcheck.model.UserInfo;

public class WechatLoginResponse {

    public WechatLoginResponse() {}

    public WechatLoginResponse(WxMaJscode2SessionResult session, UserInfo userInfo) {
        this.session = session;
        this.userInfo = userInfo;
    }

    private WxMaJscode2SessionResult session;
    private UserInfo userInfo;

    public WxMaJscode2SessionResult getSession() {
        return session;
    }

    public void setSession(WxMaJscode2SessionResult session) {
        this.session = session;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
