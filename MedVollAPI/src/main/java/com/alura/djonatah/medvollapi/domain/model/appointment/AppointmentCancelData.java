package com.alura.djonatah.medvollapi.domain.model.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelData(
        @NotNull(message = "Appointment id is required")
        Long appointmentId,
        @NotNull(message = "reason is required")
        CancelReason reason
) {
}
