import { Routes } from '@angular/router';
import { PatientComponent } from './patient/patient.component';

export const routes: Routes = [
  // Home / Tenant test (Week 1)
  { path: '', component: PatientComponent },

  // EMR module (Week 2 – Lazy Loaded)
  {
    path: 'emr',
    loadChildren: () =>
      import('./emr/emr.routes').then(m => m.EMR_ROUTES)
  }
];
