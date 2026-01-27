package com.mednex.hms.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mednex.hms.tenant.MultiTenantConnectionProviderImpl;
import com.mednex.hms.tenant.TenantIdentifierResolver;

@Configuration
public class HibernateMultiTenancyConfig {

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            TenantIdentifierResolver tenantResolver) {

        Map<String, Object> props = new HashMap<>();

        // ✅ Hibernate 6 — string based keys ONLY
        props.put("hibernate.multiTenancy", "SCHEMA");
        props.put(
            "hibernate.multi_tenant_connection_provider",
            new MultiTenantConnectionProviderImpl(dataSource)
        );
        props.put(
            "hibernate.tenant_identifier_resolver",
            tenantResolver
        );

        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.mednex.hms");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaPropertyMap(props);

        return emf;
    }
}
