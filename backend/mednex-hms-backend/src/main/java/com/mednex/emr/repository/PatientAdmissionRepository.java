package com.mednex.emr.repository;

import com.mednex.emr.entity.PatientAdmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientAdmissionRepository
        extends JpaRepository<PatientAdmission, UUID> {

    List<PatientAdmission> findByPatientId(String patientId);
}
