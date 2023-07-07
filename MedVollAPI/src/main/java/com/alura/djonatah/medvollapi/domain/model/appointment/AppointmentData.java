package com.alura.djonatah.medvollapi.domain.model.appointment;

import com.alura.djonatah.medvollapi.domain.model.doctor.Speciality;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentData(
        @JsonAlias({"doctor_id", "idDoctor"})
        Long doctorId,
        @NotNull(message = "patient is required")
        @JsonAlias({"patient_id", "idPatient"})
        Long patientId,
        @NotNull(message = "date is required")
        @Future
        //@JsonFormat(pattern = "dd/MM/yy HH:mm")
        LocalDateTime date,

        Speciality speciality) {

}
