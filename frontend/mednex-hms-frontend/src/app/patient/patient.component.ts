import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { PatientService } from './patient.service';

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatButtonToggleModule
  ],
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {

  selectedTenant = 'hospital_a';
  patients: any[] = [];
  loading = false;

  constructor(private patientService: PatientService) {
    // default tenant
    localStorage.setItem('TENANT_ID', this.selectedTenant);
  }

  selectTenant(tenant: string) {
    this.selectedTenant = tenant;
    localStorage.setItem('TENANT_ID', tenant); // ✅ REQUIRED
    this.patients = [];
  }

  loadPatients() {
    this.loading = true;

    this.patientService.getPatients().subscribe({
      next: (data) => {
        console.log('Patients from backend:', data);
        this.patients = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load patients', err);
        this.loading = false;
      }
    });
  }
}
