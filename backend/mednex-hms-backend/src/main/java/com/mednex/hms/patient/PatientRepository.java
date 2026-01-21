package com.mednex.hms.patient;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mednex.hms.tenant.TenantContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PatientRepository
{
	@PersistenceContext
    private EntityManager em;

	
    public Patient save(Patient p) {

        String schema = TenantContext.getTenant();

        if (schema == null) {
            throw new IllegalStateException("Tenant not set");
        }
        
        String sql = """
            INSERT INTO %s.patients (name, created_at)
            VALUES (:name, now())
            RETURNING id
        """.formatted(schema);

        Long id = ((Number) em.createNativeQuery(sql)
                .setParameter("name", p.getName())
                .getSingleResult()).longValue();

        p.setId(id);
        p.setCreatedAt(LocalDateTime.now());

        return p;
    }
    
    
    public List<Patient> findAll() {
        String schema = TenantContext.getTenant();

        List<Object[]> rows = em.createNativeQuery(
            "SELECT id, name, created_at FROM " + schema + ".patients"
        ).getResultList();

        return rows.stream().map(row -> {
            Patient p = new Patient();
            p.setId(((Number) row[0]).longValue());
            p.setName((String) row[1]);
            p.setCreatedAt((LocalDateTime) row[2]); // ✅ FIX
            return p;
        }).toList();
    }
    
    public void deleteAll() {
        String schema = TenantContext.getTenant();
        if (schema == null) {
            throw new IllegalStateException("Tenant not set");
        }

        em.createNativeQuery(
            "DELETE FROM " + schema + ".patients"
        ).executeUpdate();
    }

}
