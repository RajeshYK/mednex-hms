# 🏥 MedNex Enterprise – Hospital Management System (HMS)

> **Enterprise-grade, Multi-Tenant, Full-Stack Healthcare Platform**

MedNex Enterprise is a **production-style Hospital Management System** designed for a **large consortium of hospitals**.  
It demonstrates **real-world enterprise architecture**, strict **multi-tenancy**, **security**, **compliance**, and **scalable backend design**.

This project is built from a **hospital (enterprise) point of view**, not an end-user CRUD application.

---

## 🔹 Tech Stack (Full-Stack)

### Frontend
- Angular 17+
- Standalone Components
- Lazy Loading Modules
- Angular Material
- FullCalendar (Scheduling)
- Angular Charts (Analytics)
- JWT-based Role Rendering (Admin / Doctor / Nurse)

### Backend
- Spring Boot 3.x
- Spring Security + JWT
- Hibernate ORM (Schema-per-Tenant)
- PostgreSQL
- Spring Mail (Email)
- Spring Scheduler
- Spring AOP (Audit Logging)

### Database
- PostgreSQL
- Schema-per-Tenant Architecture
- JSONB (HL7 / FHIR Simulation)

---

## 🔹 Key Enterprise Features

- ✅ Multi-Tenancy (Schema-per-Hospital)
- ✅ Role-Based Access Control
- ✅ EMR with JSONB Storage
- ✅ Appointment Scheduling & Conflict Detection
- ✅ Email Confirmation & Reminders
- ✅ Analytics Dashboard
- ✅ Encrypted PDF Export
- ✅ HIPAA / GDPR Audit Logs

---

# 📅 Week-Wise Implementation

---

## ✅ Week 1 – Architecture & Multi-Tenancy

### Implemented
- Schema-per-Tenant (`hospital_a`, `hospital_b`)
- Tenant resolution using HTTP header
- Thread-safe `TenantContext`
- Cross-tenant data isolation

### Tenant Header (Mandatory)
```http
X-Tenant-ID: hospital_a
POST /auth/login
Headers:

Content-Type: application/json
X-Tenant-ID: hospital_a
Body:

{
  "email": "admin@hospital.com",
  "password": "admin123"
}
✅ Week 2 – EMR (Electronic Medical Records)
Implemented
Large Reactive Form (50+ fields)

Custom Validators

JSONB storage for flexible medical data

Tenant-isolated EMR records

🔹 Week-2 Backend APIs (Postman)
🧑 Patient Admission
POST /api/patients/admission
Headers:

Authorization: Bearer <JWT>
X-Tenant-ID: hospital_a
Body:

{
  "patientName": "Rajesh Kumar",
  "age": 45,
  "gender": "Male",
  "medicalHistory": {
    "bp": "120/80",
    "sugar": "Normal",
    "allergies": ["Penicillin"]
  }
}
📄 Get Patient EMR
GET /api/patients/{patientId}/emr
✅ Week 3 – Scheduling & Notifications
Implemented
FullCalendar integration (Angular)

Doctor appointment booking

Time slot selection

Conflict detection (no double booking)

Email confirmation

Email reminders (Spring Scheduler)

Multi-tenant safe scheduler execution

🔹 Week-3 Backend APIs (Postman)
📅 Book Appointment
POST /api/appointments
Headers:

Authorization: Bearer <JWT>
X-Tenant-ID: hospital_a
Content-Type: application/json
Body:

{
  "doctorId": "D1001",
  "patientId": "P125",
  "startTime": "2026-02-10T13:30:00",
  "endTime": "2026-02-10T14:00:00"
}
👨‍⚕️ Doctor Appointments
GET /api/appointments/doctor/{doctorId}
✅ Week 4 – Analytics, PDF Export & Compliance
Implemented
Bed Occupancy Analytics Dashboard

KPIs (Admissions / Discharges / Active Patients)

Secure Encrypted PDF Export

HIPAA / GDPR Audit Logging using Spring AOP

🔹 Week-4 Backend APIs (Postman)
📊 Bed Occupancy Analytics
GET /api/analytics/bed-occupancy
Response:

{
  "totalBeds": 120,
  "occupiedBeds": 78,
  "availableBeds": 42
}
📄 Export Patient History (Encrypted PDF)
GET /api/patients/{patientId}/export-pdf
Headers:

Authorization: Bearer <JWT>
X-Tenant-ID: hospital_a
PDF is password-protected

Export action is audit logged

🔍 Audit Logs (Compliance)
GET /api/audit/logs
Sample Response:

{
  "action": "EXPORTED",
  "entityType": "Patient",
  "entityId": "P125",
  "role": "ADMIN",
  "tenant": "hospital_a",
  "accessedAt": "2026-02-10T11:45"
}
🔐 Security & Compliance
JWT Authentication

Role-based UI rendering

Strict tenant isolation

Access audit logging

HIPAA / GDPR compliance simulation

🚀 How to Run
Backend
cd mednex-hms-backend
mvn spring-boot:run
Frontend
cd mednex-hms-frontend
npm install
ng serve
🧠 Why This Project Is Enterprise-Ready
Not a CRUD demo

Multi-tenant by design

Security & compliance focused

Scalable architecture

Production-style healthcare system

