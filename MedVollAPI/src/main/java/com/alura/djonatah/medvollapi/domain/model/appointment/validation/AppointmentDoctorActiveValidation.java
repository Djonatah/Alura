package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDoctorActiveValidation implements AppointmentScheduleValidation{

    @Autowired
    DoctorRepository doctorRepository;

    public void validate(AppointmentData appointmentData){
        var doctorId = appointmentData.doctorId();
        if(doctorId == null)
            return;

        var doctor = doctorRepository.findByIdAndActiveFalse(doctorId);
        if(doctor != null)
            throw new DataValidationException("Doctor is set as disabled: " +doctorId);
    }
}