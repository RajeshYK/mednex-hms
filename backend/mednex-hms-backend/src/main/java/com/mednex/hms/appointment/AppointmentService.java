package com.mednex.hms.appointment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mednex.hms.notification.EmailService;
import com.mednex.hms.tenant.TenantContext;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final EmailService emailService;
    // ===============================
    // Create appointment (FIXED)
    // ===============================
  
    public Appointment createAppointment(Appointment a) {

        String tenant = TenantContext.getTenant();
        if (tenant == null) {
            throw new IllegalStateException("Tenant not resolved in service layer");
        }

        if (repository.hasConflict(
                tenant,
                a.getDoctorId(),
                a.getStartTime(),
                a.getEndTime())) {

            throw new RuntimeException("Doctor already booked for this time slot");
        }

        a.setStatus("CONFIRMED");
        
        Appointment saved = repository.save(tenant, a);

        // ✅ CONFIRMATION EMAIL
        emailService.sendConfirmation(saved);

        return saved;
    }

    // ===============================
    // Doctor appointments (future)
    // ===============================
    public List<Appointment> getDoctorAppointments(String doctorId) {
        throw new UnsupportedOperationException("Doctor appointment listing not implemented yet");
    }
}
