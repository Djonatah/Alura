package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.Appointment;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentCancelData;
import com.alura.djonatah.medvollapi.domain.model.appointment.CancelReason;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.repositories.AppointmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class AppointmentCancelWithin24HValidationTest {

    @Autowired
    AppointmentCancelWithin24HValidation cancelWithin24HValidation;

    @MockBean
    AppointmentRepository appointmentRepository;

    @Test
    @DisplayName("Should not validate when cancel happens within 24h")
    public void validateCancelWithin24H(){
        AppointmentCancelData cancelData = new AppointmentCancelData(1L, CancelReason.CANCELLED_BY_PATIENT);
        LocalDateTime appointmentDate = LocalDateTime.now().plusHours(12);

        Appointment appointment = new Appointment(null, null, null, appointmentDate, null  );
        when(appointmentRepository.existsById(any())).thenReturn(true);
        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment));

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> cancelWithin24HValidation.validate(cancelData))
                .withMessageContaining("Cancel must happen 24h before the appointment date");
    }


    @Test
    @DisplayName("Should validate when cancel happens with over 24h")
    public void validateCancel24H(){
        AppointmentCancelData cancelData = new AppointmentCancelData(1L, CancelReason.CANCELLED_BY_PATIENT);
        LocalDateTime appointmentDate = LocalDateTime.now().plusHours(36);

        Appointment appointment = new Appointment(null, null, null, appointmentDate, null  );
        when(appointmentRepository.existsById(any())).thenReturn(true);
        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment));

        Assertions.assertThatNoException().isThrownBy(() -> cancelWithin24HValidation.validate(cancelData));

    }

}