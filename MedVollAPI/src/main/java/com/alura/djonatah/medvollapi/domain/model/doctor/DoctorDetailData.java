package com.alura.djonatah.medvollapi.domain.model.doctor;

import com.alura.djonatah.medvollapi.domain.model.common.Address;

public record DoctorDetailData(Long id, String name, String register, String email, boolean active, Speciality speciality, Address address) {
    public DoctorDetailData(Doctor doctor){
        this(doctor.getId(),doctor.getName(), doctor.getRegister(), doctor.getEmail(), doctor.isActive(), doctor.getSpeciality(), doctor.getAddress());
    }
}
