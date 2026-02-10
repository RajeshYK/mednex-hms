import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';

import { AuthContextService } from '../core/auth-context.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  // ❌ NO DEFAULTS (IMPORTANT)
  tenant: 'hospital_a' | 'hospital_b' | null = null;
  role: 'ADMIN' | 'DOCTOR' | 'NURSE' | null = null;

  constructor(
    private router: Router,
    private auth: AuthContextService
  ) {}

  login() {
    // Safety check
    if (!this.tenant || !this.role) {
      return;
    }

    localStorage.setItem('TENANT_ID', this.tenant);
    localStorage.setItem('ROLE', this.role);

    // ✅ ROLE-BASED LANDING
    if (this.role === 'NURSE') {
      this.router.navigate(['/app/emr/admission']);
    } else {
      // ADMIN + DOCTOR
      this.router.navigate(['/app/patients']);
    }
  }
}
