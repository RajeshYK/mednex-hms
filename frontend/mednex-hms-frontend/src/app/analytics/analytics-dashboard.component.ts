import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-analytics-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatProgressBarModule,
    MatTableModule,
    MatButtonModule
  ],
  templateUrl: './analytics-dashboard.component.html',
  styleUrls: ['./analytics-dashboard.component.css']
})
export class AnalyticsDashboardComponent {

  // 🛏️ Bed analytics
  totalBeds = 150;
  occupiedBeds = 96;

  get availableBeds() {
    return this.totalBeds - this.occupiedBeds;
  }

  get occupancyRate() {
    return Math.round((this.occupiedBeds / this.totalBeds) * 100);
  }

  // 📊 Patient KPIs
  todayAdmissions = 24;
  todayDischarges = 18;
  activePatients = 112;

  // 🔍 Audit logs (HIPAA / GDPR simulation)
  displayedColumns = ['user', 'action', 'record', 'time'];

  auditLogs = [
    {
      user: 'Dr. Kumar',
      action: 'VIEWED',
      record: 'Patient #A1023',
      time: '2026-02-01 10:15'
    },
    {
      user: 'Admin',
      action: 'EXPORTED',
      record: 'Patient #B2044',
      time: '2026-02-01 11:40'
    }
  ];

  exportPdf() {
    alert('🔐 Patient history exported as encrypted PDF (simulated)');
  }
}
