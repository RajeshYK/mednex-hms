package com.mednex.hms.appointment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager em;

    // ===============================
    // Conflict check (UNCHANGED)
    // ===============================
    public boolean hasConflict(
            String schema,
            String doctorId,
            LocalDateTime start,
            LocalDateTime end) {

        String sql = """
            SELECT count(*) FROM %s.appointments
            WHERE doctor_id = :doctorId
              AND start_time < :endTime
              AND end_time > :startTime
        """.formatted(schema);

        Number count = (Number) em.createNativeQuery(sql)
                .setParameter("doctorId", doctorId)
                .setParameter("startTime", start)
                .setParameter("endTime", end)
                .getSingleResult();

        return count.intValue() > 0;
    }

    // ===============================
    // Save appointment (UNCHANGED)
    // ===============================
    public Appointment save(String schema, Appointment a) {

        String sql = """
            INSERT INTO %s.appointments
            (doctor_id, patient_id, start_time, end_time, status, reminder_sent, created_at)
            VALUES (:doctorId, :patientId, :startTime, :endTime, :status, false, now())
            RETURNING id
        """.formatted(schema);

        Long id = ((Number) em.createNativeQuery(sql)
                .setParameter("doctorId", a.getDoctorId())
                .setParameter("patientId", a.getPatientId())
                .setParameter("startTime", a.getStartTime())
                .setParameter("endTime", a.getEndTime())
                .setParameter("status", a.getStatus())
                .getSingleResult()).longValue();

        a.setId(id);
        return a;
    }
    
    
    public boolean appointmentsTableExists(String schema) {

        String sql = """
            SELECT EXISTS (
                SELECT 1
                FROM information_schema.tables
                WHERE table_schema = :schema
                  AND table_name = 'appointments'
            )
        """;

        return (Boolean) em.createNativeQuery(sql)
                .setParameter("schema", schema)
                .getSingleResult();
    }

    // ===============================
    // 🔔 Reminder Scheduler Query (FIXED)
    // ===============================
    public List<Appointment> findAppointmentsForReminder(
            String schema,
            LocalDateTime from,
            LocalDateTime to) {

        String sql = """
            SELECT id, doctor_id, patient_id, start_time, end_time,
                   status, reminder_sent, created_at
            FROM %s.appointments
            WHERE start_time BETWEEN :from AND :to
              AND reminder_sent = false
        """.formatted(schema);

        List<Object[]> rows = em.createNativeQuery(sql)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();

        return rows.stream().map(row -> {
            Appointment a = new Appointment();
            a.setId(((Number) row[0]).longValue());
            a.setDoctorId((String) row[1]);
            a.setPatientId((String) row[2]);
            a.setStartTime(((java.sql.Timestamp) row[3]).toLocalDateTime());
            a.setEndTime(((java.sql.Timestamp) row[4]).toLocalDateTime());
            a.setStatus((String) row[5]);
            a.setReminderSent((Boolean) row[6]);
            a.setCreatedAt(((java.sql.Timestamp) row[7]).toLocalDateTime());
            return a;
        }).toList();
    }

    // ===============================
    // 🔔 Mark reminder as sent
    // ===============================
    public void markReminderSent(String schema, Long id) {

        String sql = """
            UPDATE %s.appointments
            SET reminder_sent = true
            WHERE id = :id
        """.formatted(schema);

        em.createNativeQuery(sql)
          .setParameter("id", id)
          .executeUpdate();
    }
}
