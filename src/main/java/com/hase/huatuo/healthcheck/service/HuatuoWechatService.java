package com.hase.huatuo.healthcheck.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.hase.huatuo.healthcheck.common.config.WxMaConfiguration;
import com.hase.huatuo.healthcheck.dao.UserInfoRepository;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.response.WechatLoginResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class HuatuoWechatService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	public WechatLoginResponse login(String appId, String code) throws WxErrorException {
		final WxMaService wxService = WxMaConfiguration.getMaService(appId);
		WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
		UserInfo userInfo = null;
		String openId = session.getOpenid();
		List<UserInfo> userInfos = userInfoRepository.retrieveUserInfoByAppIdOpenId(appId, openId);
		if (!CollectionUtils.isEmpty(userInfos)) {
			userInfo = userInfos.get(0);
		}
		return new WechatLoginResponse(session, userInfo);
	}
}
