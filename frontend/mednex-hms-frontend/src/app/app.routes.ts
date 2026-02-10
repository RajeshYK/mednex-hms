import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { PatientComponent } from './patient/patient.component';
import { AppointmentCalendarComponent } from './appointments/appointment-calendar.component';
import { AnalyticsDashboardComponent } from './analytics/analytics-dashboard.component';
import { RoleGuard } from './core/role.guard';

export const routes: Routes = [
<<<<<<< HEAD

  // 🔐 LOGIN PAGE
  { path: 'login', component: LoginComponent },

  // 🏥 HMS APP AFTER LOGIN
  {
    path: 'app',
    component: MainLayoutComponent,
    children: [
      {
      path: 'patients',
      component: PatientComponent,
      canActivate: [RoleGuard]   // 👈 ADD THIS
    },
      {
        path: 'emr',
        loadChildren: () =>
          import('./emr/emr.routes').then(m => m.EMR_ROUTES)
      },
      { path: 'appointments', component: AppointmentCalendarComponent },
      { path: 'analytics', component: AnalyticsDashboardComponent }
    ]
  },

  // Default redirect
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Fallback
  { path: '**', redirectTo: 'login' }
=======
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
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
];
