import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';

import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AppointmentService } from './appointment.service';

@Component({
  selector: 'app-appointment-calendar',
  standalone: true,
  imports: [
    CommonModule,
    FullCalendarModule,
    MatSnackBarModule
  ],
  templateUrl: './appointment-calendar.component.html',
  styleUrls: ['./appointment-calendar.component.css']
})
export class AppointmentCalendarComponent {

  constructor(
    private snackBar: MatSnackBar,
    private appointmentService: AppointmentService
  ) {}

  // ✅ Convert JS Date → LocalDateTime string (NO timezone)
  private toLocalDateTime(date: Date): string {
    return date.toISOString().slice(0, 19);
  }

  calendarOptions: any = {
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    initialView: 'timeGridWeek',
    selectable: true,
    editable: false,
    allDaySlot: false,
    slotMinTime: '09:00:00',
    slotMaxTime: '18:00:00',

    // prevent booking past slots
    validRange: {
      start: new Date()
    },

    select: this.onSlotSelect.bind(this)
  };

  onSlotSelect(info: any) {

    const payload = {
      doctorId: 'D1001',     // later dynamic
      patientId: 'P125',     // later from UI
      startTime: this.toLocalDateTime(info.start),
      endTime: this.toLocalDateTime(info.end)
    };

    this.appointmentService.createAppointment(payload).subscribe({
      next: () => {
        this.snackBar.open(
          'Appointment booked successfully',
          'OK',
          { duration: 3000, verticalPosition: 'top' }
        );
      },
      error: (err) => {
        this.snackBar.open(
          err?.error?.message || 'Slot already booked',
          'OK',
          { duration: 3000, verticalPosition: 'top' }
        );
      }
    });
  }
}
