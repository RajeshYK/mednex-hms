package com.mednex.hms.notification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mednex.hms.appointment.Appointment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

    public void sendConfirmation(Appointment appointment) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("patient@example.com"); // demo email
        message.setSubject("Appointment Confirmation");

        message.setText(
            "Your appointment is confirmed.\n\n" +
            "Doctor: " + appointment.getDoctorId() + "\n" +
            "Start Time: " + appointment.getStartTime() + "\n" +
            "End Time: " + appointment.getEndTime()
        );

        mailSender.send(message);
    }
    
    public void sendReminder(Appointment appointment) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("patient@example.com"); // demo email
        message.setSubject("Appointment Reminder");

        message.setText(
            "Reminder: Your appointment is scheduled soon.\n\n" +
            "Doctor: " + appointment.getDoctorId() + "\n" +
            "Start Time: " + appointment.getStartTime()
        );

        mailSender.send(message);
    }
 
    
    
}