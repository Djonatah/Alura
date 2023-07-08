package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentSheduleMinimumTimeValidation implements AppointmentScheduleValidation{

    public void validate(AppointmentData appointmentData){
        var now = LocalDateTime.now();
        var scheduleDate = appointmentData.date();
        var diffInMinutes = Duration.between(now,scheduleDate).toMinutes();
        if(diffInMinutes < 30)
            throw new DataValidationException("Unable to schedule appointment - Appointment should be scheduled with at least 30min");
    }
}
