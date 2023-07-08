package com.alura.djonatah.medvollapi.domain.repositories;

import com.alura.djonatah.medvollapi.domain.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime start, LocalDateTime end);

    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime scheduleDate);
}
