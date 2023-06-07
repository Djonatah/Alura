package com.alura.djonatah.medvollapi.domain.model.doctor;

public record DoctorListData(Long id, String name, String register, String email, boolean active, Speciality speciality) {
    public DoctorListData(Doctor doctor){
        this(doctor.getId(),doctor.getName(), doctor.getRegister(), doctor.getEmail(), doctor.isActive(), doctor.getSpeciality());
    }
}
