import { Injectable } from '@angular/core';

export type UserRole = 'ADMIN' | 'DOCTOR' | 'NURSE';

@Injectable({ providedIn: 'root' })
export class AuthContextService {

  getRole(): 'ADMIN' | 'DOCTOR' | 'NURSE' | null {
    return localStorage.getItem('ROLE') as any;
  }

  isAdmin() {
    return this.getRole() === 'ADMIN';
  }

  isDoctor() {
    return this.getRole() === 'DOCTOR';
  }

  isNurse() {
    return this.getRole() === 'NURSE';
  }

  logout() {
    localStorage.clear();
  }
}
