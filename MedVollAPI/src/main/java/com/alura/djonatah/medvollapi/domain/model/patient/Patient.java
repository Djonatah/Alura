package com.alura.djonatah.medvollapi.domain.model.patient;

import com.alura.djonatah.medvollapi.domain.model.common.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patient")
@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ssn;
    private String phone;
    private String email;
    private boolean active;
    @Embedded
    private Address address;
    public Patient(PatientData patientData){
        this.name = patientData.name();
        this.ssn = patientData.ssn();
        this.phone = patientData.phone();
        this.email = patientData.email();
        this.address = new Address(patientData.address());
        this.active = true;
    }
}
