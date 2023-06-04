package com.alura.djonatah.medvollapi.domain.Doctor;

import com.alura.djonatah.medvollapi.domain.common.Address;
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
    @Enumerated
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(DoctorData doctorData) {
        this.name = doctorData.name();
        this.email = doctorData.email();
        this.register = doctorData.register();
        this.phone = doctorData.phone();
        this.speciality = doctorData.speciality();
        this.address = new Address(doctorData.address());
    }
}
