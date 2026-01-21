package com.mednex.emr.controller;

import com.mednex.emr.entity.PatientAdmission;
import com.mednex.emr.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService service;

    /**
     * Save Patient Admission (Week 2)
     */
    @PostMapping
    public ResponseEntity<?> saveAdmission(
            @RequestParam String patientId,
            @RequestBody Map<String, Object> admissionData) {

        PatientAdmission saved =
                service.saveAdmission(patientId, admissionData);

        return ResponseEntity.ok(saved.getId());
    }

    /**
     * Fetch patient admissions
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<List<PatientAdmission>> getAdmissions(
            @PathVariable String patientId) {

        return ResponseEntity.ok(
                service.getAdmissionsByPatient(patientId)
        );
    }
}
