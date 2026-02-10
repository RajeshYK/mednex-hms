package com.mednex.hms.audit;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "tenant", nullable = false)
    private String tenant;

    // 🔥 FIX IS HERE
    @Column(name = "accessed_at", nullable = false)
    private LocalDateTime accessedAt;

    @PrePersist
    public void onCreate() {
        this.accessedAt = LocalDateTime.now();
    }
}
