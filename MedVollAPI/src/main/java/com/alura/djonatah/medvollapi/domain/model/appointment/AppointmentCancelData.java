package com.alura.djonatah.medvollapi.domain.model.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record AppointmentCancelData(
        @NotNull(message = "Appointment id is required")
        @JsonAlias("id")
        Long appointmentId,
        @NotNull(message = "reason is required")
        @Enumerated(value = EnumType.STRING)
        CancelReason reason
) {
}
