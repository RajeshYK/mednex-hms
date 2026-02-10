package com.mednex.hms.appointment;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    // ===============================
    // Book appointment
    // ===============================
    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        return service.createAppointment(appointment);
    }

    // ===============================
    // Get doctor appointments
    // ===============================
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getByDoctor(@PathVariable String doctorId) {
        return service.getDoctorAppointments(doctorId);
    }
}
