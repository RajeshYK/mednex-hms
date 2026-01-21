package com.mednex.hms.tenant;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("!test")
public class TenantCleanupAspect {

    @After("execution(* com.mednex.hms..*(..))")
    public void clearTenantContext() {
        TenantContext.clear();
    }
}
