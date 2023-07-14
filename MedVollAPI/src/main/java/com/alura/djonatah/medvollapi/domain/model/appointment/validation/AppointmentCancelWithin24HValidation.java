package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentCancelData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentCancelWithin24HValidation implements AppointmentCancelValitation{

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(AppointmentCancelData appointmentCancelData) {
        var appointmentId =appointmentCancelData.appointmentId();
        if(! appointmentRepository.existsById(appointmentId))
            return;

        var appointment = appointmentRepository.findById(appointmentId).get();
        var isWithin24h = appointment.getDate().isBefore(LocalDateTime.now().plusHours(24));
        if(isWithin24h)
            throw new DataValidationException("Unable to cancel appointment - Cancel must happen 24h before the appointment date");
    }
}
