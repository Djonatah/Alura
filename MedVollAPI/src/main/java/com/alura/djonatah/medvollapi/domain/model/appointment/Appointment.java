package com.alura.djonatah.medvollapi.domain.model.appointment;

import com.alura.djonatah.medvollapi.domain.model.doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.model.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    Patient patient ;

    LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    CancelReason cancelReason;
}
