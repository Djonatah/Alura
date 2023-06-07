package com.alura.djonatah.medvollapi.domain.model.doctor;

import com.alura.djonatah.medvollapi.domain.model.common.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String register;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(DoctorRegisterData doctorData) {
        this.name = doctorData.name();
        this.email = doctorData.email();
        this.register = doctorData.register();
        this.phone = doctorData.phone();
        this.speciality = doctorData.speciality();
        this.address = new Address(doctorData.address());
        this.active = true;
    }
}
