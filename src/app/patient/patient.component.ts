import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientService } from './patient.service';

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="card">
      <h2>Tenant Patient View</h2>

      <div class="tenant-buttons">
        <button class="tenant-btn" (click)="setTenant('hospital_a')">
          Hospital A
        </button>

        <button class="tenant-btn" (click)="setTenant('hospital_b')">
          Hospital B
        </button>
      </div>

      <button class="load-btn" (click)="loadPatients()">
        Load Patients
      </button>

      <ul *ngIf="patients.length > 0">
        <li *ngFor="let p of patients">{{ p.name }}</li>
      </ul>

      <p *ngIf="patients.length === 0" class="hint">
    
      </p>
    </div>
  `
})
export class PatientComponent {
  patients: any[] = [];

  constructor(private service: PatientService) {}

  setTenant(tenant: string) {
    localStorage.setItem('TENANT_ID', tenant);
    alert(`Tenant set to ${tenant}`);
    this.patients = [];
  }

  loadPatients() {
  const tenant = localStorage.getItem('TENANT_ID');

  if (!tenant) {
    alert('Please select Hospital A or B first');
    return;
  }

  this.service.getPatients().subscribe({
    next: res => this.patients = res,
    error: err => alert(err.error)
  });
  }
}
