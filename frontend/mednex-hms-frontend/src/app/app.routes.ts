import { Routes } from '@angular/router';
import { PatientComponent } from './patient/patient.component';

export const routes: Routes = [
  // =========================
  // Week 1 – Default Screen
  // =========================
  {
    path: '',
    component: PatientComponent
  },

  // =========================
  // Week 2 – EMR Module
  // URL: /emr/admission
  // =========================
  {
    path: 'emr',
    loadChildren: () =>
      import('./emr/emr.routes').then(m => m.EMR_ROUTES)
  },

  // =========================
  // Fallback (Optional but Recommended)
  // =========================
  {
    path: '**',
    redirectTo: ''
  }
];
