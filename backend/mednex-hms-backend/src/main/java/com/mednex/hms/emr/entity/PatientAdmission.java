package com.mednex.hms.emr.entity;

import java.time.LocalDateTime;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient_admission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAdmission {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private String patientId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "admission_data",columnDefinition = "jsonb"
    )
    private Map<String, Object> admissionData;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
