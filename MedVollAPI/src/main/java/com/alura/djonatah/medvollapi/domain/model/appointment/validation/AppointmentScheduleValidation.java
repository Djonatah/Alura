package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;

public interface AppointmentScheduleValidation {
    void validate(AppointmentData appointmentData);
}
