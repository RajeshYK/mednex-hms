package com.mednex.hms.emr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mednex.hms.emr.entity.PatientAdmission;

@Repository
public interface PatientAdmissionRepository
        extends JpaRepository<PatientAdmission, Long> {

    List<PatientAdmission> findByPatientId(String patientId);
}
