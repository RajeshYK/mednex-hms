# 🏥 MedNex Enterprise – Backend (Hospital Management System)

## Overview

MedNex Enterprise is a production-grade **multi-tenant Hospital Management System (HMS)** backend designed to support multiple hospitals within a single application while enforcing **strict data isolation**.

This backend is built using **Spring Boot 3, Hibernate 6, and PostgreSQL** with a **Schema-per-Tenant** architecture.  
Currently, **Week 1 and Week 2** milestones are fully implemented.

---

## 🧱 Technology Stack

- Java 21
- Spring Boot 3
- Hibernate 6 / Spring Data JPA
- PostgreSQL
- Schema-per-Tenant Multi-Tenancy
- JSONB (PostgreSQL)
- Virtual Threads enabled

---

## 📅 Week 1 – Architecture & Multi-Tenancy

### 🎯 Goal

Implement a **robust schema-based multi-tenant architecture** where each hospital operates in a **logically and physically isolated database schema**.

---

### ✅ Features Implemented

- Schema-per-Tenant strategy using Hibernate
- Separate schemas per hospital:
  - `hospital_a`
  - `hospital_b`
- Dynamic schema switching per request
- Custom tenant resolution using HTTP headers
- Thread-safe tenant context handling

---

### 🔄 Tenant Resolution Flow

1. Client sends request with:
2. `TenantFilter` extracts tenant identifier
3. `TenantContext` stores tenant using ThreadLocal
4. `TenantIdentifierResolver` supplies tenant to Hibernate
5. `MultiTenantConnectionProvider` sets PostgreSQL `search_path`
6. Queries execute within the tenant’s schema

---

### 🔒 Data Isolation

- Hospital A data is fully isolated from Hospital B
- Cross-tenant access is prevented at database level
- Schema-level enforcement ensures strong security boundaries

---

### 🧪 Sample API (Week 1)

GET http://localhost:8080/patients
Headers : X-TENANT-ID: hospital_a


---

## 📅 Week 2 – Electronic Medical Records (EMR)

### 🎯 Goal

Design a **flexible, scalable medical records backend** capable of handling complex and evolving healthcare data structures.

---

### ✅ Features Implemented

- Patient Admission API
- Medical data stored as PostgreSQL **JSONB**
- Semi-structured storage aligned with HL7/FHIR concepts
- Tenant-aware EMR persistence
- Spring Data JPA integration

---

### 🧠 Why JSONB?

- Supports large, nested medical data
- Avoids frequent schema migrations
- Ideal for medical interoperability standards
- Optimized for future analytics

---

### 🧪 Sample API (Week 2)
POST
http://localhost:8080/api/admissions?patientId=P124
Headers:
X-TENANT-ID: hospital_a
Content-Type: application/json


body 
--------
{
  "personal": {
    "firstName": "Rahul",
    "lastName": "Kumar",
    "age": 32,
    "gender": "Male",
    "phone": "9876543210",
    "address": "Hyderabad"
  },
  "vitals": {
    "bp": "120/80",
    "pulse": "72",
    "temperature": "98.6",
    "spo2": "99"
  },
  "diagnosis": {
    "chiefComplaint": "Fever",
    "provisionalDiagnosis": "Viral Fever",
    "finalDiagnosis": ""
  },
  "history": [
    {
      "condition": "Diabetes",
      "since": "2018"
    }
  ],
  "medications": [
    {
      "name": "Paracetamol",
      "dose": "500mg",
      "frequency": "2 times a day"
    }
  ],
  "allergies": [],
  "notes": "Patient stable"
}


