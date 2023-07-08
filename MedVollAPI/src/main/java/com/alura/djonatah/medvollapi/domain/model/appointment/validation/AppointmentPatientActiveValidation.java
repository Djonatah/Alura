package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentPatientActiveValidation implements AppointmentScheduleValidation{

    @Autowired
    PatientRepository patientRepository;

    public void validate(AppointmentData appointmentData){
        var patientId = appointmentData.patientId();
        var patient = patientRepository.findById(patientId);
        if(patient.isEmpty() || !patient.get().isActive())
            throw new DataValidationException("Patient is set as disabled: " +patientId);
    }
}