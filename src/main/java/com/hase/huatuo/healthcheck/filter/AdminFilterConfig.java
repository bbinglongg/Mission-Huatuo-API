package com.hase.huatuo.healthcheck.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminFilterConfig {

    @Autowired
    private HuatuoAdminFilter filter;

    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/huatuo-admin/api/*");
        filterRegistrationBean.setName("HuatuoAdminFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
