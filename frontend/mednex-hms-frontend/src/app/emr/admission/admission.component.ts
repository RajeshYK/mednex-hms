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

  private buildForm(): FormGroup {
    return this.fb.group({
      personal: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        age: ['', [Validators.required, Validators.min(0)]],
        gender: ['', Validators.required],
        phone: [''],
        address: ['']
      }),

      vitals: this.fb.group({
        bp: ['', [Validators.required, bpValidator]],
        pulse: [''],
        temperature: [''],
        spo2: ['']
      }),

      diagnosis: this.fb.group({
        chiefComplaint: ['', Validators.required],
        provisionalDiagnosis: [''],
        finalDiagnosis: ['']
      }),

      history: this.fb.array([]),
      medications: this.fb.array([]),
      allergies: this.fb.array([]),

      insurance: this.fb.group({
        provider: [''],
        policyNumber: ['']
      }),

      notes: ['']
    });
  }

  // ---------- FORM ARRAYS ----------

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

  // ---------- SUBMIT ----------

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.service.saveAdmission(this.form.value).subscribe({
      next: () => alert('Admission Saved'),
      error: () => alert('Save Failed')
    });
  }
}
