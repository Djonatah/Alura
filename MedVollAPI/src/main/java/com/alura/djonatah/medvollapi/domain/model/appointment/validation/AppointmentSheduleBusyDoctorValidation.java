package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentSheduleBusyDoctorValidation implements AppointmentScheduleValidation{

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(AppointmentData appointmentData){
        var now = LocalDateTime.now();
        var doctorId = appointmentData.doctorId();
        var scheduleDate = appointmentData.date();

        if(appointmentRepository.existsByDoctorIdAndDate(doctorId, scheduleDate))
            throw new DataValidationException("Unable to schedule appointment - Doctor is already schedule for in this date");
    }
}
