package com.hase.huatuo.healthcheck.utils;

import com.hase.huatuo.healthcheck.common.config.WxMaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class AppConfigUtils {

    @Autowired
    private WxMaProperties wxMaProperties;

    private static AppConfigUtils appConfigUtils;

    @PostConstruct
    public void init() {
        appConfigUtils = this;
        appConfigUtils.wxMaProperties = this.wxMaProperties;
    }

    public static String getAppIdFromProperties(String appName) {
        Optional<WxMaProperties.Config> hacnAppIdConfigOptional = appConfigUtils.wxMaProperties.getConfigs().stream().filter(config -> appName.equalsIgnoreCase(config.getAppName())).findFirst();
        if (hacnAppIdConfigOptional.isPresent()) {
            return hacnAppIdConfigOptional.get().getAppid();
        }
        return null;
    }
}
