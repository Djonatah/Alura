package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentPatientSingleScheduleValidation implements AppointmentScheduleValidation{

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(AppointmentData appointmentData){
        var patientId = appointmentData.patientId();
        var appointmentDate = appointmentData.date();
        var start = appointmentDate.withHour(7);
        var end = appointmentDate.withHour(18);
        if(!appointmentRepository.existsByPatientIdAndDateBetween(patientId, start, end))
                throw new DataValidationException("Patient is already scheduled for an appointment in the same day");
        }
    }