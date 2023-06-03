package com.alura.djonatah.medvollapi.domain.Doctor;

import com.alura.djonatah.medvollapi.domain.common.AddressData;

public record DoctorData(String name, String email, Speciality speciality, AddressData address) {
}
