package com.mednex.hms.config;


import com.mednex.hms.tenant.MultiTenantConnectionProviderImpl;
import com.mednex.hms.tenant.TenantIdentifierResolver;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateMultiTenancyConfig {

    @Bean
    public Map<String, Object> hibernateProperties(
            DataSource dataSource,
            TenantIdentifierResolver tenantResolver) {

        Map<String, Object> props = new HashMap<>();

        props.put("hibernate.multiTenancy", "SCHEMA");
        props.put(
          "hibernate.multi_tenant_connection_provider",
          new MultiTenantConnectionProviderImpl(dataSource)
        );
        props.put(
          "hibernate.tenant_identifier_resolver",
          tenantResolver
        );

        return props;
    }
}
