package com.hase.huatuo.healthcheck.common.config;

import org.apache.commons.lang3.ArrayUtils;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DozerMapperConfig {

    @Bean
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozer-mapping/*.xml") Resource[] resources) {
        final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        if (!ArrayUtils.isEmpty(resources)) {
            dozerBeanMapperFactoryBean.setMappingFiles(resources);
        }
        return dozerBeanMapperFactoryBean;
    }
}
