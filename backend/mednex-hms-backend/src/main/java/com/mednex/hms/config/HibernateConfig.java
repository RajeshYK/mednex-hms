package com.mednex.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mednex.hms.tenant.MultiTenantConnectionProviderImpl;
import com.mednex.hms.tenant.TenantIdentifierResolver;

@Configuration
public class HibernateConfig {

    @Bean
    public TenantIdentifierResolver tenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    @Bean
    public MultiTenantConnectionProviderImpl multiTenantConnectionProvider(
            javax.sql.DataSource dataSource) {
        return new MultiTenantConnectionProviderImpl(dataSource);
    }
}
