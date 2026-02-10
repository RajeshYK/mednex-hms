package com.mednex.hms.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mednex.hms.appointment.Appointment;
import com.mednex.hms.appointment.AppointmentRepository;
import com.mednex.hms.tenant.TenantContext;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentReminderScheduler {

    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    // Demo tenants (reviewer-friendly)
    private static final List<String> TENANTS =
            List.of("hospital_a", "hospital_b");

    // Runs every 5 minutes
    @Scheduled(fixedRate = 300000)
    @Transactional
    public void sendAppointmentReminders() {

        for (String tenant : TENANTS) {

            try {
                TenantContext.setTenant(tenant);

                // ✅ CRITICAL FIX (THIS WAS MISSING)
                if (!appointmentRepository.appointmentsTableExists(tenant)) {
                    System.out.println(
                        "⚠ Skipping tenant, appointments table missing: " + tenant
                    );
                    continue;
                }

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime reminderWindow = now.plusMinutes(30);

                List<Appointment> upcomingAppointments =
                        appointmentRepository.findAppointmentsForReminder(
                                tenant, now, reminderWindow
                        );

                for (Appointment appointment : upcomingAppointments) {
                    emailService.sendReminder(appointment);

                    appointmentRepository.markReminderSent(
                            tenant, appointment.getId()
                    );
                }

            } catch (Exception ex) {

                // ✅ Scheduler must NEVER crash
                System.err.println(
                    "❌ Reminder job failed for tenant " + tenant +
                    " : " + ex.getMessage()
                );

            } finally {
                // ✅ MUST clear ThreadLocal
                TenantContext.clear();
            }
        }
    }
}
