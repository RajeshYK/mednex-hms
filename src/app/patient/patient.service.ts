import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class PatientService {

  private api = 'http://localhost:8080/patients';

  constructor(private http: HttpClient) {}

  getPatients() {
    return this.http.get<any[]>(this.api);
  }
}
