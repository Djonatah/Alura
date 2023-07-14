package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AppointmentSheduleMinimumTimeValidationTest {

    @Autowired
    AppointmentSheduleMinimumTimeValidation minimumTimeValidation;

    @Test
    @DisplayName("Validates that appointment date does not meet minimum time required")
    public void validateBelowMinimumTimeBeforeAppointment(){
        var appointmentDate = LocalDateTime.now().plusMinutes(25);
        var appointment = new AppointmentData(null, null, appointmentDate, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> minimumTimeValidation.validate(appointment))
                .withMessageContaining("Appointment should be scheduled with at least");
    }

    @Test
    @DisplayName("Validates that appointment date meets minimum time required")
    public void validateOverMinimumTimeBeforeAppointment(){
        var appointmentDate = LocalDateTime.now().plusMinutes(35);
        var appointment = new AppointmentData(null, null, appointmentDate, null);

        Assertions.assertThatNoException().isThrownBy(() -> minimumTimeValidation.validate(appointment));
    }

}