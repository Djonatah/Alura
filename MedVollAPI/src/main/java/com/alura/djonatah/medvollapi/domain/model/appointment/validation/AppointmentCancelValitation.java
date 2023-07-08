package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentCancelData;

public interface AppointmentCancelValitation {
    void validate(AppointmentCancelData applicationCancelData);
}
