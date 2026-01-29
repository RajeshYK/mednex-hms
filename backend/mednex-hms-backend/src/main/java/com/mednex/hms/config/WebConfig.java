package com.mednex.hms.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mednex.hms.tenant.TenantFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<TenantFilter> tenantFilter() {
        FilterRegistrationBean<TenantFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(new TenantFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);

        return registration;
    }
}
