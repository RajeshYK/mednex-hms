import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { AdmissionService } from './admission.service';
import { bpValidator } from '../validators/medical.validators';

@Component({
  selector: 'app-admission',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './admission.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AdmissionComponent {

  form: FormGroup;

  constructor(private fb: FormBuilder, private service: AdmissionService) {
    this.form = this.buildForm();
  }

<<<<<<< HEAD
  // ================= BUILD FORM =================

  private buildForm(): FormGroup {
    return this.fb.group({

      // -------- PERSONAL DETAILS --------
=======
  private buildForm(): FormGroup {
    return this.fb.group({
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      personal: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        age: ['', [Validators.required, Validators.min(0)]],
        gender: ['', Validators.required],
        phone: [''],
        address: ['']
      }),

<<<<<<< HEAD
      // -------- VITALS --------
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      vitals: this.fb.group({
        bp: ['', [Validators.required, bpValidator]],
        pulse: [''],
        temperature: [''],
        spo2: ['']
      }),

<<<<<<< HEAD
      // -------- DIAGNOSIS --------
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      diagnosis: this.fb.group({
        chiefComplaint: ['', Validators.required],
        provisionalDiagnosis: [''],
        finalDiagnosis: ['']
      }),

<<<<<<< HEAD
      // -------- EMERGENCY CONTACT --------
      emergency: this.fb.group({
        contactName: [''],
        contactPhone: [''],
        relation: ['']
      }),

      // -------- LIFESTYLE --------
      lifestyle: this.fb.group({
        smoking: [''],
        alcohol: [''],
        exercise: ['']
      }),

      // -------- FAMILY HISTORY --------
      familyHistory: this.fb.group({
        diabetes: [false],
        hypertension: [false],
        cancer: [false]
      }),

      // -------- LAB RESULTS --------
      labResults: this.fb.group({
        hemoglobin: [''],
        sugar: [''],
        cholesterol: ['']
      }),

      // -------- DYNAMIC ARRAYS --------
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      history: this.fb.array([]),
      medications: this.fb.array([]),
      allergies: this.fb.array([]),

<<<<<<< HEAD
      // -------- INSURANCE --------
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      insurance: this.fb.group({
        provider: [''],
        policyNumber: ['']
      }),

<<<<<<< HEAD
      // -------- NOTES --------
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      notes: ['']
    });
  }

<<<<<<< HEAD
  // ================= FORM ARRAYS =================
=======
  // ---------- FORM ARRAYS ----------
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd

  get history(): FormArray {
    return this.form.get('history') as FormArray;
  }

  addHistory() {
    this.history.push(
      this.fb.group({
        condition: ['', Validators.required],
        since: ['']
      })
    );
  }

  get medications(): FormArray {
    return this.form.get('medications') as FormArray;
  }

  addMedication() {
    this.medications.push(
      this.fb.group({
        name: ['', Validators.required],
        dose: [''],
        frequency: ['']
      })
    );
  }

  get allergies(): FormArray {
    return this.form.get('allergies') as FormArray;
  }

  addAllergy() {
    this.allergies.push(
      this.fb.group({
        allergen: ['', Validators.required],
        reaction: ['']
      })
    );
  }

<<<<<<< HEAD
  // ================= SUBMIT =================
=======
  // ---------- SUBMIT ----------
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

<<<<<<< HEAD
    const patientId = 'P123'; // TEMP → later from route / auth / state

    this.service.saveAdmission(patientId, this.form.value).subscribe({
=======
    this.service.saveAdmission(this.form.value).subscribe({
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
      next: () => alert('Admission Saved'),
      error: () => alert('Save Failed')
    });
  }
<<<<<<< HEAD

  // ================= PERFORMANCE =================
  trackByIndex(index: number): number {
    return index;
  }
=======
>>>>>>> 58469bf046b0b5736d13d956c81f9c73f0d8b4fd
}
