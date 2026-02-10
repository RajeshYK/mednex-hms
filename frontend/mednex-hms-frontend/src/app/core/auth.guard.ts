import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const role = localStorage.getItem('ROLE');
    const tenant = localStorage.getItem('TENANT_ID');

    if (role && tenant) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
