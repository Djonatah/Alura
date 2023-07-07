package com.alura.djonatah.medvollapi.domain.repositories;

import com.alura.djonatah.medvollapi.domain.model.doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.model.doctor.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
    @Query("""
            select d
            from Doctor d
            where
                d.active = True
                and d.speciality = :speciality
                and d.id not in (select a.doctor.id from Appointment a where a.date = :appointmentDate)
            order by rand()
            limit 1
            """)
    Doctor selectCalendarFreeDoctorForAppointment(Speciality speciality, LocalDateTime appointmentDate);
}
