package com.mednex.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mednex.hms.patient.Patient;
import com.mednex.hms.patient.PatientRepository;
import com.mednex.hms.tenant.TenantContext;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MednexHmsBackendApplicationTests {
	
	@Autowired
    private PatientRepository repo;

    @Test
    void tenantIsolationMustWork() {

    	TenantContext.setTenant("hospital_a");
        repo.deleteAll();
        repo.save(new Patient("Alice"));
        assertEquals(1, repo.findAll().size());

        TenantContext.setTenant("hospital_b");
        repo.deleteAll();
        assertEquals(0, repo.findAll().size());

        TenantContext.setTenant("hospital_a");
        assertEquals(1, repo.findAll().size());
    }
}