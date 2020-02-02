package com.hase.huatuo.healthcheck.common.config;

import com.hase.huatuo.healthcheck.filter.RequestHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;

@Configuration
public class FilterConfig {
    @Bean
    public Filter requestHeader(){
        return  new RequestHeaderFilter();
    }

    @Bean
    public FilterRegistrationBean httpHeaderRequest() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(requestHeader());
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

}
