import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AdmissionService {

  private api = 'http://localhost:8080/api/admissions';

  constructor(private http: HttpClient) {}

<<<<<<< HEAD
 saveAdmission(patientId: string, data: any) {
  return this.http.post(
    `${this.api}?patientId=${patientId}`,
    data
  );
}
=======
  saveAdmission(data: any) {
    return this.http.post(this.api, data);
  }
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
}
