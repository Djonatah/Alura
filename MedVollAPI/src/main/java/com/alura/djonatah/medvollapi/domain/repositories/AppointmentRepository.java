package com.alura.djonatah.medvollapi.domain.repositories;

import com.alura.djonatah.medvollapi.domain.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
