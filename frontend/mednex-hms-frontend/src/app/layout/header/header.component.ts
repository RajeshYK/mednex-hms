import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule
  ],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  role = localStorage.getItem('ROLE');

  constructor(private router: Router) {}

  logout() {
    localStorage.clear();          // 🔐 clear session
    this.router.navigate(['/login']); // 🔁 back to login
  }
}
