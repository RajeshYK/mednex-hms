import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const role = localStorage.getItem('ROLE');

    if (role === 'ADMIN' || role === 'DOCTOR') {
      return true;
    }

    // Nurse trying to access Patients → redirect
    this.router.navigate(['/app/emr/admission']);
    return false;
  }
}
