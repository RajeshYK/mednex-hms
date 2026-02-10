import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AdmissionService {

  private api = 'http://localhost:8080/api/admissions';

  constructor(private http: HttpClient) {}

 saveAdmission(patientId: string, data: any) {
  return this.http.post(
    `${this.api}?patientId=${patientId}`,
    data
  );
}
}
