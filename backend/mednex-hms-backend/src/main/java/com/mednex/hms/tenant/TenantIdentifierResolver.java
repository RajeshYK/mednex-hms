package com.mednex.hms.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
	    String tenant = TenantContext.getTenant();
	    System.out.println(">>> RESOLVED TENANT = " + tenant);
	    return tenant != null ? tenant : "public";
	}

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
    
}
