package com.mednex.hms.emr.service;

import com.mednex.hms.emr.entity.PatientAdmission;
import com.mednex.hms.emr.repository.PatientAdmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    private final PatientAdmissionRepository repository;

    public PatientAdmission saveAdmission(String patientId,
                                          Map<String, Object> data) {

    	if (data.size() > 200) {
            throw new IllegalArgumentException("Admission payload too large");
        }
    	
        PatientAdmission admission = PatientAdmission.builder()
                .patientId(patientId)
                .admissionData(data)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(admission);
    }

    public List<PatientAdmission> getAdmissionsByPatient(String patientId) {
        return repository.findByPatientId(patientId);
    }
}
