package com.mednex.hms.emr.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mednex.hms.emr.entity.PatientAdmission;
import com.mednex.hms.emr.service.AdmissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService service;

    
    @PostMapping
    public ResponseEntity<Long> saveAdmission( @RequestParam String patientId, @RequestBody Map<String, Object> admissionData) {

        PatientAdmission saved =service.saveAdmission(patientId, admissionData);

        return ResponseEntity.ok(saved.getId());
    }

   
    @GetMapping("/{patientId}")
    public ResponseEntity<List<PatientAdmission>> getAdmissions( @PathVariable String patientId) {

        return ResponseEntity.ok(service.getAdmissionsByPatient(patientId));
    }
}
