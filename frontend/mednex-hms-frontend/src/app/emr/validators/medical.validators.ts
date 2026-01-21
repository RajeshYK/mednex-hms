import { AbstractControl } from '@angular/forms';

export function bpValidator(control: AbstractControl) {
  const value = control.value;
  if (!value) return null;

  return /^\d{2,3}\/\d{2,3}$/.test(value)
    ? null
    : { invalidBP: true };
}
