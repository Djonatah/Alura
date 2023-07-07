package com.alura.djonatah.medvollapi.domain.model.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
}
