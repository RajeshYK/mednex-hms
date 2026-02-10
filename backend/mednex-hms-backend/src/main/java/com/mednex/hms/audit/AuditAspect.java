package com.mednex.hms.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository repository;

    @AfterReturning(
        value = "execution(* com.mednex.hms..controller.*.*(..))",
        returning = "result"
    )
    public void logAccess(JoinPoint joinPoint, Object result) {

        AuditLog log = new AuditLog();

        log.setAction(joinPoint.getSignature().getName().toUpperCase());
        log.setEntityType(joinPoint.getTarget().getClass().getSimpleName());

        // ✅ SAFE entityId resolution
        log.setEntityId(resolveEntityId(result));

        log.setRole(getCurrentRole());
        log.setTenant(getCurrentTenant());

        repository.save(log);
    }

    private String resolveEntityId(Object result) {
        if (result == null) return "N/A";

        if (result instanceof Long || result instanceof String) {
            return result.toString();
        }

        try {
            var method = result.getClass().getMethod("getId");
            Object id = method.invoke(result);
            return id != null ? id.toString() : "UNKNOWN";
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    private String getCurrentRole() {
        return System.getProperty("ROLE", "ADMIN");
    }

    private String getCurrentTenant() {
        return System.getProperty("TENANT", "hospital_a");
    }
}
