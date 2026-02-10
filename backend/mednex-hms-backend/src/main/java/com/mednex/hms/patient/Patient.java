package com.mednex.hms.patient;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // ✅ FIX #1: Correct column name mapping
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ✅ FIX #2: Let Hibernate set timestamp
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Required by JPA
    protected Patient() {}

    public Patient(String name) {
        this.name = name;
    }
}
