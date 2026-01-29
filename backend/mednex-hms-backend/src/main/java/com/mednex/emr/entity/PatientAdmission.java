package com.mednex.emr.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "patient_admission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAdmission {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String patientId;

    /**
     * HL7 / FHIR-like flexible medical record
     */
    @Type(org.hibernate.type.JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> admissionData;

    private LocalDateTime createdAt;
}
