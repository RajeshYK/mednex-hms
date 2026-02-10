package com.mednex.hms.patient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mednex.hms.tenant.TenantContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PatientRepository {

    @PersistenceContext
    private EntityManager em;

    // ============================
    // CREATE PATIENT
    // ============================
    public Patient save(Patient p) {

        String schema = TenantContext.getTenant();
        if (schema == null) {
            throw new IllegalStateException("Tenant not set");
        }

        String sql = """
            INSERT INTO %s.patients (name, created_at)
            VALUES (:name, now())
            RETURNING id, created_at
        """.formatted(schema);

        Object[] result = (Object[]) em.createNativeQuery(sql)
                .setParameter("name", p.getName())
                .getSingleResult();

        p.setId(((Number) result[0]).longValue());

        // ✅ FIX: Timestamp → LocalDateTime
        p.setCreatedAt(((Timestamp) result[1]).toLocalDateTime());

        return p;
    }

    // ============================
    // FETCH ALL PATIENTS
    // ============================
    public List<Patient> findAll() {

        String schema = TenantContext.getTenant();
        if (schema == null) {
            throw new IllegalStateException("Tenant not set");
        }

        List<Object[]> rows = em.createNativeQuery(
            "SELECT id, name, created_at FROM " + schema + ".patients"
        ).getResultList();

        return rows.stream().map(row -> {
            Patient p = new Patient();
            p.setId(((Number) row[0]).longValue());
            p.setName((String) row[1]);

            // ✅ FIX: Timestamp → LocalDateTime
            Timestamp ts = (Timestamp) row[2];
            p.setCreatedAt(ts.toLocalDateTime());

            return p;
        }).toList();
    }

    // ============================
    // DELETE ALL (OPTIONAL)
    // ============================
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
